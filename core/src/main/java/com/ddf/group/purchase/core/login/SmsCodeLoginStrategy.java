package com.ddf.group.purchase.core.login;

import com.ddf.group.purchase.api.enume.LoginTypeEnum;
import com.ddf.group.purchase.api.request.common.SmsCodeVerifyRequest;
import com.ddf.group.purchase.api.request.user.LoginRequest;
import com.ddf.group.purchase.core.helper.CommonHelper;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import com.ddf.group.purchase.core.service.UserInfoService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>验证码登录</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/26 11:24
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class SmsCodeLoginStrategy implements LoginStrategy {
    private final UserInfoRepository userInfoRepository;
    private final CommonHelper commonHelper;
    private final UserInfoService userInfoService;

    /**
     * 登录类型
     *
     * @return
     */
    @Override
    public LoginTypeEnum getLoginType() {
        return LoginTypeEnum.SMS_CODE;
    }

    /**
     * 短信验证码登录
     *
     * @param loginRequest
     * @return
     */
    @Override
    public UserInfo login(LoginRequest loginRequest) {
        final String mobile = loginRequest.getLoginIdentity();
        final String smsCode = loginRequest.getCredential();
        UserInfo userInfo = userInfoRepository.getByMobile(mobile);
        // 短信验证码校验
        final SmsCodeVerifyRequest verifyRequest = SmsCodeVerifyRequest.builder()
                .mobile(mobile)
                .mobileCode(smsCode)
                .uuid(loginRequest.getUuid())
                .build();
        commonHelper.verifySmsCode(verifyRequest);
        // 用户不存在，则执行简单注册
        if (Objects.isNull(userInfo)) {
            userInfo = userInfoService.registerUserInfo(mobile, null);
        }
        return userInfo;
    }
}
