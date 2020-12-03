package com.example.springboot.controller;

import com.example.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

//    // 这里指定模板  不然就会默认去访问 public 里面的index.html
//    // 也可以使用 MyMvcConfig 中添加视图映射
//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user) {
        System.out.println(user);
        if(user.equals("")) {
            System.out.println("出现异常");
            throw new UserNotExistException();
        }
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map) {
        // 模拟一些数据，展示在页面中
        map.put("msg","hello");
        map.put("users", Arrays.asList("张三","李四","王五"));
        return "success";
    }
}
