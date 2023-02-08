package com.brodog.springframework.bean;

/**
 * @author By-BroDog
 * @createTime 2023-02-04
 */
public class UserService {
    private String userName;

    private UserDao userDao;

    public UserService(String userName) {
        this.userName = userName;
    }

    public void insertUser() {
        System.out.println("添加用户成功: " + userName);
    }

    public void findUserName(String userId) {
        userDao.findUser(userId);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
