package com.brodog.aop.controller;

import com.brodog.aop.annotation.PassTokenRequired;
import com.brodog.aop.entity.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author By-Lin
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @PassTokenRequired
    @RequestMapping("/test")
    public ApiResult testToken() {
        return new ApiResult(200,"请求成功");
    }
}
