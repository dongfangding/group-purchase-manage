package com.ddf.group.purchase.model.request;

import com.ddf.boot.common.core.validator.constraint.Mobile;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>用户注册请求类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/15 20:59
 */
@Data
public class UserRegistryRequest implements Serializable {

    private static final long serialVersionUID = 1516322558409231083L;

    /**
     * 小区id
     */
    @NotNull(message = "小区id不能为空")
    private Integer communityId;

    /**
     * 楼栋号
     */
    @NotNull(message = "楼栋号不能为空")
    private Integer buildingNo;

    /**
     * 房间号
     */
    @NotNull(message = "房间号不能为空")
    private Integer roomNo;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Mobile
    private String mobile;

    /**
     * 手机验证码
     */
    @NotBlank(message = "手机验证码不能为空")
    private String mobileCode;

    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;
}
