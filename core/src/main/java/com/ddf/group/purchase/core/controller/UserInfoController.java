package com.ddf.group.purchase.core.controller;

import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.group.purchase.api.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.api.request.user.EmailVerifyRequest;
import com.ddf.group.purchase.api.request.user.ModifyPasswordRequest;
import com.ddf.group.purchase.api.response.user.PersonalInfoResponse;
import com.ddf.group.purchase.core.application.UserApplicationService;
import com.ddf.group.purchase.core.client.MailClient;
import com.ddf.group.purchase.core.helper.CommonHelper;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>用户资料相关</p >
 *
 * @menu 用户资料相关
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 20:54
 */
@RestController
@RequestMapping("/userInfo")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserInfoController {

    private final UserApplicationService userApplicationService;
    private final CommonHelper commonHelper;
    private final MailClient mailClient;

    /**
     * 完善用户信息
     *
     * @param request
     */
    @PostMapping("completeInfo")
    public PersonalInfoResponse completeInfo(@RequestBody @Validated CompleteUserInfoRequest request) {
        return userApplicationService.completeInfo(request);
    }

    /**
     * 单独发送邮箱验证邮件
     *
     * @param request
     */
    @PostMapping("sendEmailVerify")
    public void sendEmailVerify(@RequestBody @Validated EmailVerifyRequest request) {
        mailClient.sendEmailActive(UserContextUtil.getLongUserId(), request.getEmail());
    }

    /**
     * 验证邮箱
     *
     * @param response
     * @param token
     */
    @GetMapping("verifyEmailActiveToken")
    public void verifyEmailActiveToken(HttpServletResponse response, @RequestParam String token) {
        commonHelper.verifyEmailActiveToken(response, token);
    }

    /**
     * 个人中心
     *
     */
    @GetMapping("personalInfo")
    public PersonalInfoResponse personalInfo() {
        return userApplicationService.personalInfo();
    }

    @PostMapping("modifyPassword")
    public void modifyPassword(@RequestBody @Validated ModifyPasswordRequest request) {
        userApplicationService.modifyPassword(request);
    }
}
