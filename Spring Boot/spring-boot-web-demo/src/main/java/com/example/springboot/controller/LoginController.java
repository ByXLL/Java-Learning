package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

//    @RequestMapping(value = "user/login",method = RequestMethod.POST) 可以简写
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("username") String password, Map<String,Object> map, HttpSession session) {

        if("admin".equals(username)  && "admin".equals(password)) {
            // 设置session
            session.setAttribute("loginUser",username);
            // 防止表单重复提交 使用重定向 到 main
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
}
