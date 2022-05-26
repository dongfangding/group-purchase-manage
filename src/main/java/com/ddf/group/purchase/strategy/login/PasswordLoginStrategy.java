package com.ddf.group.purchase.strategy.login;

import com.ddf.boot.common.core.encode.BCryptPasswordEncoder;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.constants.LoginTypeEnum;
import com.ddf.group.purchase.exception.ExceptionCode;
import com.ddf.group.purchase.model.entity.UserInfo;
import com.ddf.group.purchase.model.request.user.LoginRequest;
import com.ddf.group.purchase.repository.UserInfoRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>密码登录</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/26 11:24
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class PasswordLoginStrategy implements LoginStrategy {
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录类型
     *
     * @return
     */
    @Override
    public LoginTypeEnum getLoginType() {
        return LoginTypeEnum.PASSWORD;
    }

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @Override
    public UserInfo login(LoginRequest loginRequest) {
        final String mobile = loginRequest.getLoginIdentity();
        final String password = loginRequest.getCredential();
        final UserInfo userInfo = userInfoRepository.getByMobile(mobile);
        PreconditionUtil.checkArgument(Objects.nonNull(userInfo), ExceptionCode.MOBILE_NOT_REGISTERED);
        PreconditionUtil.checkArgument(bCryptPasswordEncoder.matches(password, userInfo.getPassword()), ExceptionCode.LOGIN_PASSWORD_ERROR);
        return userInfo;
    }
}
