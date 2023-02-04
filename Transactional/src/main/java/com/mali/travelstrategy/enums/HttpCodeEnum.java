package com.mali.travelstrategy.enums;

import lombok.Getter;

/**
 * http 响应 code 枚举类
 * @author By-Lin
 */
@Getter
public enum HttpCodeEnum {
    /** 成功 **/
    SUCCESS(200,"操作成功"),
    /** 参数异常 **/
    ARG_ERROR(400,"参数异常"),
    /** 权限异常 **/
    AUTH_ERROR(401,"权限异常"),
    /** 操作失败 **/
    ERROR(500,"操作失败");
    private final Integer code;
    private final String desc;

    HttpCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
