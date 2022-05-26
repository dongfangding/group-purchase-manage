package com.ddf.group.purchase.strategy.login;

import com.ddf.group.purchase.constants.LoginTypeEnum;
import com.ddf.group.purchase.model.request.user.LoginRequest;
import com.ddf.group.purchase.model.response.UserLoginResponse;

/**
 * <p>登录策略接口</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/23 23:38
 */
public interface LoginStrategy {

    /**
     * 登录类型
     * @return
     */
    LoginTypeEnum getLoginType();

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    UserLoginResponse login(LoginRequest loginRequest);

}
