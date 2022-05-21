package com.ddf.group.purchase.helper;

import cn.hutool.core.util.RandomUtil;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.ext.sms.SmsApi;
import com.ddf.boot.common.ext.sms.model.SmsSendRequest;
import com.ddf.boot.common.ext.sms.model.SmsSendResponse;
import com.ddf.common.captcha.helper.CaptchaHelper;
import com.ddf.common.captcha.model.CaptchaRequest;
import com.ddf.common.captcha.model.CaptchaResult;
import com.ddf.group.purchase.constants.RedisKeyEnum;
import com.ddf.group.purchase.constants.RedisKeys;
import com.ddf.group.purchase.model.request.SendSmsCodeRequest;
import com.ddf.group.purchase.model.response.ApplicationSmsSendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    public CaptchaResult generateCaptcha(CaptchaRequest request) {
        return captchaHelper.generate(request);
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
     * 发送并存储短信验证码，并返回绑定的tokenId（随机参数，客户端回传进行表单绑定）
     *
     * @param sendSmsCodeRequest
     * @return
     */
    public ApplicationSmsSendResponse sendAndLoadSmsCode(SendSmsCodeRequest sendSmsCodeRequest) {
        final String mobile = sendSmsCodeRequest.getMobile();
        final SmsSendResponse tempResponse = sendSmsCode(sendSmsCodeRequest);
        final String random = RandomUtil.randomString(16);
        stringRedisTemplate.opsForValue().set(RedisKeys.getSmsCodeKey(mobile, random), tempResponse.getRandomCode(),
                RedisKeyEnum.SMS_CODE_KEY.getTimeout());
        return ApplicationSmsSendResponse.builder()
                .tokenId(random)
                .build();
    }
}
