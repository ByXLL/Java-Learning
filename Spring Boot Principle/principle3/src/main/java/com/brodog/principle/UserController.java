package com.brodog.principle;

import com.byxll.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1、模拟service
 * 通过自定义注解的方式将当前bean装配到ioc容器中去
 * @author By-Lin
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public StringUtil stringUtil;

    @RequestMapping("/login")
    public void login() {
        System.out.println(stringUtil.getNewString());
    }
}
