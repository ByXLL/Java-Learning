package com.brodog.principle;

/**
 * 1、模拟service
 * 通过自定义注解的方式将当前bean装配到ioc容器中去
 * @author By-Lin
 */
public class UserService {
    public void login() {
        System.out.println("用户登录");
    }
}
