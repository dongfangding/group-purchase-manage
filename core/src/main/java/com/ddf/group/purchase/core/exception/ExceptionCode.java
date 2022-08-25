package com.ddf.group.purchase.core.exception;

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
    VERIFY_CODE_NOT_MATCH("verify_code_not_match", "验证码不匹配"),
    SMS_CODE_LIMIT("SMS_CODE_LIMIT", "今日短信发送额度已用完，无法继续使用~"),
    MOBILE_NOT_REGISTERED("mobile_not_registered", "手机号尚未注册~"),
    LOGIN_PASSWORD_ERROR("login_password_error", "密码不匹配，请确认~"),
    LOGIN_STRATEGY_MAPPING_ERROR("login_strategy_mapping_error", "登录策略异常~", "服务器开小差了~"),
    EMAIL_ACTIVE_TOKEN_EXPIRED("email_active_token_expired", "激活链接已过期，请重新验证"),
    USER_NOT_EXIST("user_not_exist", "用户不存在"),
    GROUP_MASTER_NOT_MAPPING("group_master_not_mapping", "身份不匹配，请确认接龙中个人信息与系统登记的楼栋号和房间号相匹配~"),
    RECORD_NOT_EXIST("record_not_exist", "记录不存在"),
    RECORD_STATUS_NOT_ALLOW_MODIFY("record_status_not_allow_modify", "当前状态不允许修改"),
    DATA_NOT_MATCH_USER("data_not_match_user", "数据和用户身份不匹配"),

    ;

    /**
     * 异常状态码
     */
    private final String code;

    /**
     * 异常消息
     */
    private final String description;

    /**
     * 返回给前端的异常消息
     */
    private final String bizMessage;

    ExceptionCode(String code, String description) {
        this.code = code;
        this.description = description;
        this.bizMessage = description;
    }

    ExceptionCode(String code, String description, String bizMessage) {
        this.code = code;
        this.description = description;
        this.bizMessage = bizMessage;
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
