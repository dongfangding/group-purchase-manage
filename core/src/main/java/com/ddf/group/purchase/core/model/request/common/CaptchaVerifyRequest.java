package com.ddf.group.purchase.core.model.request.common;

import com.ddf.common.captcha.constants.CaptchaType;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>验证码校验参数相关</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/27 09:43
 */
@Data
public class CaptchaVerifyRequest implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * uuid 请求验证码接口返回的
     */
    @NotBlank(message = "uuid不能为空")
    private String uuid;

    /**
     * 验证码
     */
    @NotBlank(message = "tokenId不能为空")
    private String verifyCode;

    /**
     * 验证码类型
     */
    @NotNull(message = "验证码类型不能为空")
    private CaptchaType captchaType = CaptchaType.MATH;
}
