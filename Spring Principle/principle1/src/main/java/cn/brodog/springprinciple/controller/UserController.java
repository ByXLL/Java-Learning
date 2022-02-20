package cn.brodog.springprinciple.controller;

import cn.brodog.springprinciple.service.UserService;

/**
 * user controller
 * @author By-Lin
 */
public class UserController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
