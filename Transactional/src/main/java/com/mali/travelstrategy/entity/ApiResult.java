package com.mali.travelstrategy.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 全局响应数据格式对象
 * @author By-mali
 */
@Getter
@Setter
public class ApiResult {
    private Integer code;
    private String msg;
    private Object data;

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
