package com.ddf.group.purchase.exception;

import com.ddf.boot.common.core.exception200.BaseCallbackCode;

/**
 * <p>异常状态码</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/21 11:00
 */
public enum ExceptionCode implements BaseCallbackCode {
    /**
     * 异常定义
     *
     */
    MOBILE_IS_USED("mobile_is_used", "手机号已被使用"),
    VERIFY_CODE_EXPIRED("verify_code_expired", "验证码已过期"),
    VERIFY_CODE_NOT_MATCH("verify_code_not_match", "验证码不匹配")

    ;

    private final String code;

    private final String description;

    ExceptionCode(String code, String description) {
        this.code = code;
        this.description = description;
    }


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
