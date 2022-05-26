package com.ddf.group.purchase.model.request.user;

import com.ddf.group.purchase.constants.LoginTypeEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>登录请求</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/23 23:25
 */
@Data
public class LoginRequest implements Serializable {

    /**
     * 登录方式
     */
    private LoginTypeEnum loginType = LoginTypeEnum.PASSWORD;

    /**
     * 登录身份（用户名、手机号）
     */
    private String loginIdentity;

    /**
     * 登录凭据（密码、验证码等）
     */
    private String credential;
}
