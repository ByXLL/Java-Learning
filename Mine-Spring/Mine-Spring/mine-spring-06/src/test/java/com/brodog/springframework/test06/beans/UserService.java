package com.brodog.springframework.test06.beans;

/**
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class UserService {
    private String userName;

    private String userId;

    private UserDao userDao;

    private String company;

    private String location;

    public UserService() {
    }

    public UserService(String userName) {
        this.userName = userName;
    }

    public void insertUser() {
        System.out.println("userId: " + userId);
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", userDao=" + userDao +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
