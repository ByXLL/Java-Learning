package com.mali.travelstrategy.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局错误处理 控制器
 * @author  By-mali
 */
@RestController
public class ErrorController extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        Map<String,Object> apiResult = new HashMap<>(16);
        apiResult.put("code",errorAttributes.get("status"));
        apiResult.put("msg",errorAttributes.get("error"));
        apiResult.put("data",errorAttributes.get("message"));
        return apiResult;
    }
}
