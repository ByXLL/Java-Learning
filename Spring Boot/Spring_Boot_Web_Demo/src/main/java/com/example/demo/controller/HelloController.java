package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单一个类都是做 请求响应的时候 可以把 @ResponseBody 当前类前面
 * ResponseBody 代表返回响应体
 * Controller
 * 可以使用 RestController 一个代用
 */

@RestController
public class HelloController {
    /**
     * 设置一个请求
     * RequestMapping("/hello") 代表 路由
     * @return 返回的信息
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}
