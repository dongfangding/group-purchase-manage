package com.ddf.group.purchase.core.helper;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.ddf.boot.common.authentication.config.AuthenticationProperties;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.util.WebUtil;
import com.ddf.boot.common.ext.sms.SmsApi;
import com.ddf.boot.common.ext.sms.model.SmsSendRequest;
import com.ddf.boot.common.ext.sms.model.SmsSendResponse;
import com.ddf.boot.common.redis.helper.RedisTemplateHelper;
import com.ddf.common.captcha.helper.CaptchaHelper;
import com.ddf.common.captcha.model.request.CaptchaCheckRequest;
import com.ddf.common.captcha.model.request.CaptchaRequest;
import com.ddf.common.captcha.model.response.ApplicationCaptchaResult;
import com.ddf.group.purchase.api.request.common.CaptchaVerifyRequest;
import com.ddf.group.purchase.api.request.common.SendSmsCodeRequest;
import com.ddf.group.purchase.api.request.common.SmsCodeVerifyRequest;
import com.ddf.group.purchase.api.response.common.ApplicationSmsSendResponse;
import com.ddf.group.purchase.core.config.properties.ApplicationProperties;
import com.ddf.group.purchase.core.constants.RedisKeys;
import com.ddf.group.purchase.core.exception.ExceptionCode;
import com.ddf.group.purchase.core.model.dto.user.EmailToken;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.repository.CommonRepository;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>通用帮助类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 22:58
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CommonHelper {

    private final SmsApi aliYunSmsApiImpl;
    private final CaptchaHelper captchaHelper;
    private final AuthenticationProperties authenticationProperties;
    private final RedisTemplateHelper redisTemplateHelper;
    private final UserInfoRepository userInfoRepository;
    private final CommonRepository commonRepository;
    private final ApplicationProperties applicationProperties;

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    public ApplicationCaptchaResult generateCaptcha(CaptchaRequest request) {
        return ApplicationCaptchaResult.fromCaptchaResult(captchaHelper.generate(request));
    }

    /**
     * 验证码校验
     *
     * @param request
     */
    public void verifyCaptcha(CaptchaVerifyRequest request) {
        captchaHelper.check(CaptchaCheckRequest.builder()
                .uuid(request.getUuid())
                .verification(request.isVerification())
                .captchaVerification(request.getCaptchaVerification())
                .captchaType(request.getCaptchaType())
                .verifyCode(request.getVerifyCode())
                .build());
    }

    /**
     * 发送短信验证码
     *
     * @param sendSmsCodeRequest
     */
    public SmsSendResponse sendSmsCode(SendSmsCodeRequest sendSmsCodeRequest) {
        PreconditionUtil.requiredParamCheck(sendSmsCodeRequest);
        final SmsSendRequest request = new SmsSendRequest();
        request.setPhoneNumbers(sendSmsCodeRequest.getMobile());
        return aliYunSmsApiImpl.send(request);
    }

    /**
     * 发送注册短信验证码，并返回绑定的tokenId（随机参数，客户端回传进行表单绑定），有限流
     *
     * @param sendSmsCodeRequest
     * @return
     */
    public ApplicationSmsSendResponse sendAndLoadRegisterSmsCodeWithLimit(SendSmsCodeRequest sendSmsCodeRequest) {
        PreconditionUtil.checkArgument(!userInfoRepository.exitsByMobile(sendSmsCodeRequest.getMobile()),
                ExceptionCode.MOBILE_IS_USED);
        return sendAndLoadSmsCodeWithLimit(sendSmsCodeRequest);
    }

    /**
     * 发送并存储短信验证码，并返回绑定的tokenId（随机参数，客户端回传进行表单绑定），有限流
     *
     * @param sendSmsCodeRequest
     * @return
     */
    public ApplicationSmsSendResponse sendAndLoadSmsCodeWithLimit(SendSmsCodeRequest sendSmsCodeRequest) {
        // 验证码校验
        verifyCaptcha(sendSmsCodeRequest.getCaptchaVerifyRequest().setVerification(true));
        final String uid = StrUtil.blankToDefault(UserContextUtil.getUserId(), WebUtil.getHost());
        return redisTemplateHelper.sliderWindowAccessExpiredAtCheckException(
                RedisKeys.getSmsRateLimitKey(uid),
                applicationProperties.getSmsDailyLimit(), DateUtils.getEndOfDay(new Date()), () -> {
            return sendAndLoadSmsCode(sendSmsCodeRequest);
        }, ExceptionCode.SMS_CODE_LIMIT);
    }

    /**
     * 发送并存储短信验证码，并返回绑定的tokenId（随机参数，客户端回传进行表单绑定）， 无限流
     *
     * @param sendSmsCodeRequest
     * @return
     */
    public ApplicationSmsSendResponse sendAndLoadSmsCode(SendSmsCodeRequest sendSmsCodeRequest) {
        final String mobile = sendSmsCodeRequest.getMobile();
        final SmsSendResponse tempResponse = sendSmsCode(sendSmsCodeRequest);
        final String uuid = RandomUtil.randomString(16);
        commonRepository.setSmsCode(mobile, uuid, tempResponse.getRandomCode());
        return ApplicationSmsSendResponse.builder()
                .uuid(uuid)
                .build();
    }

    /**
     * 校验短信验证码， 可使用白名单功能
     *
     * @param request
     * @return
     */
    public void verifySmsCode(SmsCodeVerifyRequest request) {
        PreconditionUtil.requiredParamCheck(request);
        String mobile = request.getMobile();
        // 验证码白名单忽略处理
        if (Objects.isNull(authenticationProperties) || CollectionUtil.isEmpty(authenticationProperties.getBiz().getWhiteLoginNameList())
                || !authenticationProperties.getBiz().getWhiteLoginNameList().contains(request.getMobile())) {
            // 校验验证码
            String verifyCode = commonRepository.getSmsCode(mobile, request.getUuid());
            PreconditionUtil.checkArgument(StrUtil.isNotBlank(verifyCode), ExceptionCode.VERIFY_CODE_EXPIRED);
            PreconditionUtil.checkArgument(StrUtil.equals(verifyCode, request.getMobileCode()), ExceptionCode.VERIFY_CODE_NOT_MATCH);
        }
    }

    /**
     * 验证邮箱链接激活
     *
     * @param response
     * @param token
     */
    public void verifyEmailActiveToken(HttpServletResponse response, String token) {
        final EmailToken emailToken = commonRepository.getEmailActiveToken(token);
        PreconditionUtil.checkArgument(Objects.nonNull(emailToken), ExceptionCode.EMAIL_ACTIVE_TOKEN_EXPIRED);

        final Long userId = emailToken.getUserId();
        final String email = emailToken.getEmail();
        final UserInfo userInfo = userInfoRepository.getById(userId);
        PreconditionUtil.checkArgument(Objects.nonNull(userInfo), ExceptionCode.USER_NOT_EXIST);
        int i = userInfoRepository.verifiedEmail(userId, email);
        response.setContentType("text/html;charset=utf-8");
        if (i > 0) {
            WebUtil.responseSuccess(response, "验证成功");
        } else {
            WebUtil.responseError(response, 400, "验证失败，链接可能已过期或邮箱已验证通过~");
        }
    }
}
