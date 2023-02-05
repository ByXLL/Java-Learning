package com.brodog.springframework.bean;

/**
 * @author By-BroDog
 * @createTime 2023-02-04
 */
public class UserService {
    private String userName;

    public UserService(String userName) {
        this.userName = userName;
    }

    public void insertUser() {
        System.out.println("添加用户成功: " + userName);
    }
}
