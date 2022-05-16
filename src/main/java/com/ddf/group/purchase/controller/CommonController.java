package com.ddf.group.purchase.controller;

import com.ddf.common.captcha.model.CaptchaRequest;
import com.ddf.common.captcha.model.CaptchaResult;
import com.ddf.group.purchase.helper.CommonHelper;
import com.ddf.group.purchase.model.request.SendSmsCodeRequest;
import com.ddf.group.purchase.model.response.CaptchaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>通用工具类</p >
 *
 * @menu 通用工具类
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 23:02
 */
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CommonController {

    private final CommonHelper commonHelper;

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    @PostMapping("/generateCaptcha")
    public CaptchaResponse generateCaptcha(@RequestBody @Validated CaptchaRequest request) {
        final CaptchaResult generate = commonHelper.generateCaptcha(request);
        final CaptchaResponse response = new CaptchaResponse();
        response.setWidth(generate.getWidth());
        response.setHeight(generate.getHeight());
        response.setTokenId(generate.getToken());
        response.setBase64(generate.getImageBase64());
        response.setPrefix(generate.getPrefix());
        return response;
    }

    /**
     * 发送短信验证码
     *
     * @param sendSmsCodeRequest
     * @return
     */
    @PostMapping("/sendSmsCode")
    public void sendSmsCode(@RequestBody @Validated SendSmsCodeRequest sendSmsCodeRequest) {
        commonHelper.sendSmsCode(sendSmsCodeRequest);
    }
}
