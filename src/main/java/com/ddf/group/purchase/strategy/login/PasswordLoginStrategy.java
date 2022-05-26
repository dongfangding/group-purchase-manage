package com.ddf.group.purchase.strategy.login;

import com.ddf.group.purchase.constants.LoginTypeEnum;
import com.ddf.group.purchase.model.request.user.LoginRequest;
import com.ddf.group.purchase.model.response.UserLoginResponse;
import org.springframework.stereotype.Service;

/**
 * <p>密码登录</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/26 11:24
 */
@Service
public class PasswordLoginStrategy implements LoginStrategy {
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
    public UserLoginResponse login(LoginRequest loginRequest) {
        return null;
    }
}
