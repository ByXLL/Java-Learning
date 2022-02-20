package cn.brodog.springprinciple.controller;

import cn.brodog.springprinciple.AutoWired;
import cn.brodog.springprinciple.service.UserService;

/**
 * @author By-Lin
 */
public class AdminController {
    @AutoWired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

}
