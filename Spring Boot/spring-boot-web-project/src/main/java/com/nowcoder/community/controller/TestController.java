package com.nowcoder.community.controller;

import com.nowcoder.community.util.CommunityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/test")
public class TestController {

    // cookie 演示
    @GetMapping("/cookie/set")
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie  设置cookie名字 内容为随机uuid
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置生效范围
        cookie.setPath("/community/test");
        // cookie 设置生存时间
        cookie.setMaxAge(60*10);
        response.addCookie(cookie);
        return  "Set cookie";
    }

    // 获取Cookie 演示
    @GetMapping("/cookie/get")
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    // session 示例
    @ResponseBody
    @GetMapping("/session/set")
    public String setSession(HttpSession session) {
        // 往session里存数据
        session.setAttribute("id",1);
        session.setAttribute("name", "张三");

        return "set Session";
    }

    // session 示例
    @ResponseBody
    @GetMapping("/session/get")
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "Get Session";
    }

    // ajax 示例
    @GetMapping("/ajaxTest")
    @ResponseBody
    public String ajaxTest() {
        return CommunityUtil.getJsonString(0,"获取成功");
    }
}
