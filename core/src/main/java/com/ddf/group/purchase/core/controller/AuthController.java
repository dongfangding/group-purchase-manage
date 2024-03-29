package com.ddf.group.purchase.core.controller;

import com.ddf.boot.common.authentication.model.UserClaim;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.group.purchase.api.request.common.SendSmsCodeRequest;
import com.ddf.group.purchase.api.request.user.LoginRequest;
import com.ddf.group.purchase.api.request.user.UserRegistryRequest;
import com.ddf.group.purchase.api.response.common.ApplicationSmsSendResponse;
import com.ddf.group.purchase.api.response.common.UserLoginResponse;
import com.ddf.group.purchase.core.application.UserApplicationService;
import com.ddf.group.purchase.core.helper.CommonHelper;
import com.ddf.group.purchase.core.login.LoginStrategyContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>认证控制器</p >
 *
 * @menu 认证控制器
 * @author Snowball
 * @version 1.0
 * @date 2022/05/26 22:56
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class AuthController {

    private final CommonHelper commonHelper;
    private final UserApplicationService userApplicationService;
    private final LoginStrategyContext loginStrategyContext;

    /**
     * 发送注册短信验证码, 会校验手机号是否已被注册，已被注册，无法发送
     *
     * @param sendSmsCodeRequest
     * @return
     */
    @PostMapping("sendRegisterSmsCode")
    public ApplicationSmsSendResponse sendRegisterSmsCode(@RequestBody @Validated SendSmsCodeRequest sendSmsCodeRequest) {
        return commonHelper.sendAndLoadRegisterSmsCodeWithLimit(sendSmsCodeRequest);
    }

    /**
     * 注册
     *
     * @param request
     */
    @PostMapping("registry")
    public void registry(@RequestBody @Validated UserRegistryRequest request) {
        userApplicationService.registry(request);
    }

    /**
     * 登录
     *
     * @param request
     */
    @PostMapping("login")
    public UserLoginResponse login(@RequestBody @Validated LoginRequest request) {
        return loginStrategyContext.login(request);
    }

    /**
     * 获取当前用户认证信息
     *
     * @return
     */
    @GetMapping("currentUser")
    public UserClaim currentUser() {
        return UserContextUtil.getUserClaim();
    }

}
