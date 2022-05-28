package com.ddf.group.purchase.controller;

import com.ddf.group.purchase.model.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @PostMapping("completeInfo")
    public void completeInfo(@RequestBody @Validated CompleteUserInfoRequest request) {
        userInfoService.completeInfo(request);
    }
}
