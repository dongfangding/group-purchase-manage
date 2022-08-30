package com.ddf.group.purchase.api.request.common;

import com.ddf.common.captcha.constants.CaptchaType;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>验证码校验参数相关</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/27 09:43
 */
@Data
@Accessors(chain = true)
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
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    /**
     * 验证码类型{@link CaptchaType}
     */
    @NotNull(message = "验证码类型不能为空")
    private CaptchaType captchaType = CaptchaType.MATH;

    /**
     * 是否是二次接口认证，验证码先是前端校验一次，后端会再次校验一次，前端的这个值是false, 后端的必须是true
     */
    private boolean verification;

    /**
     * 二次校验参数值，由前端控件生成
     */
    private String captchaVerification;
}
