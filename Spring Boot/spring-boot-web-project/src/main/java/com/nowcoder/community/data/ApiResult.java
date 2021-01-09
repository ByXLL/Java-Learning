package com.nowcoder.community.data;

/**
 * api 返回接口 数据格式基类
 */
public class ApiResult {
    private Integer code;
    private String msg;
    private Object data;
    private String errorCode;

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ApiResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
