package com.brodog.aop.controller;

import com.brodog.aop.annotation.MyLog;
import com.brodog.aop.annotation.PassTokenRequired;
import com.brodog.aop.entity.ApiResult;
import com.brodog.aop.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author By-Lin
 */
@RequestMapping("/log")
@RestController
public class LogController {

    @PassTokenRequired
    @MyLog(title = "日志模块", data = "test接口")
    @RequestMapping("/test")
    public ApiResult testLogAop(@RequestBody User user) {
        System.out.println("----------------- 处理业务  -----------------");
        return new ApiResult(200,"请求成功", user);
    }
}
