package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        // 认证成功后 security 会默认 通过 securityContextHolder 存入 securityContext中
        // 通过 getPrincipal 获取 在 UsernamePasswordAuthenticationToken 声明的 Credentials （第一个参数）
        Object object =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(object);
        // 判断当前这结果是不是 User
        if(object instanceof User) {
            model.addAttribute("loginUser",object);
        }
        return "/index";
    }

    @RequestMapping(path = "/discuss", method = RequestMethod.GET)
    public String getDiscussPage() {
        return "/site/discuss";
    }

    @RequestMapping(path = "/letter", method = RequestMethod.GET)
    public String getLetterPage() {
        return "/site/letter";
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "/site/admin";
    }

    @RequestMapping(path = "/loginpage", method = {RequestMethod.GET, RequestMethod.POST})
    public String getLoginPage() {
        return "/site/login";
    }

    // 没有权限时的提示页面
    @GetMapping("/denied")
    public String getDeniedPage() {
        return "/error/404";
    }
}
