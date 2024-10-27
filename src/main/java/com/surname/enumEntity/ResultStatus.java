package com.surname.enumEntity;

import lombok.Getter;

/**
 * 返回信息枚举类
 */
public enum ResultStatus{
    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 错误的请求，参数异常
     */
    BAD_REQUEST(400, "参数为空"),
    /**
     * 401 未认证异常
     */
    UNAUTHORIZED(401, "认证异常"),
    /**
     * 4002 用户不存在
     */
    USER_NOT_FIND(4002, "用户不存在"),
    /**
     * 4003 用户名为空
     */
    USER_NAME_NULL(4003, "用户名为空"),
    /**
     * 4005 用户密码为空
     */
    USER_PASSWORD_NULL(4005, "用户密码为空"),
    /**
     * 4006 用户密码为空
     */
    USER_PASSWORD_ERROR(4006, "用户密码输入错误"),
    /**
     * 4007 用户密码为空
     */
    ACTION_REQUEST_NULL(4007, "未获取到有效用户规则"),
    /**
     * 4008 用户不存在
     */
    USER_EXIST(4008, "用户已存在"),
    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(500, "系统异常"),
    ;

    @Getter
    private int code;
    @Getter
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
