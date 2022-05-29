package com.ddf.group.purchase.core.controller;

import com.ddf.group.purchase.api.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.core.helper.CommonHelper;
import com.ddf.group.purchase.core.service.UserInfoService;
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
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 20:54
 */
@RestController
@RequestMapping("/userInfo")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CommonHelper commonHelper;

    /**
     * 完善用户信息
     *
     * @param request
     */
    @PostMapping("completeInfo")
    public void completeInfo(@RequestBody @Validated CompleteUserInfoRequest request) {
        userInfoService.completeInfo(request);
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
}
