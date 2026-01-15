package com.ddf.group.purchase.core.controller;

import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.group.purchase.api.request.user.CompleteUserInfoRequest;
import com.ddf.group.purchase.api.request.user.EmailVerifyRequest;
import com.ddf.group.purchase.api.request.user.ModifyPasswordRequest;
import com.ddf.group.purchase.api.request.user.UserAddressRequest;
import com.ddf.group.purchase.api.response.user.PersonalInfoResponse;
import com.ddf.group.purchase.api.response.user.UserAddressResponse;
import com.ddf.group.purchase.core.application.UserApplicationService;
import com.ddf.group.purchase.core.client.MailClient;
import com.ddf.group.purchase.core.converter.UserAddressConvert;
import com.ddf.group.purchase.core.helper.CommonHelper;
import com.ddf.group.purchase.core.repository.UserAddressRepository;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
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
    private final UserAddressRepository userAddressRepository;

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

    /**
     * 用户收货地址维护
     *
     * @param request
     */
    @PostMapping("address/saveOrUpdate")
    public void modifyPassword(@RequestBody @Validated UserAddressRequest request) {
        userApplicationService.addressCommand(request);
    }


    /**
     * 查询用户的收货地址
     *
     * @return
     */
    @GetMapping("address/all")
    public List<UserAddressResponse> listUserAddress() {
        return userApplicationService.listUserAddress(UserContextUtil.getLongUserId());
    }

    /**
     * 查询用户的默认收货地址
     *
     * @return
     */
    @GetMapping("address/default")
    public UserAddressResponse getUserDefaultAddress() {
        return UserAddressConvert.INSTANCE.convert(userAddressRepository.getUserDefaultAddress(UserContextUtil.getLongUserId()));
    }

    /**
     * 删除用户收货地址
     *
     * @param id
     */
    @PostMapping("address/delete")
    public int deleteUserAddress(@RequestParam Long id) {
        return userApplicationService.deleteUserAddress(id, UserContextUtil.getLongUserId());
    }
}
