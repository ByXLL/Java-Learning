package com.brodog.cor;

/**
 * @author By-BroDog
 * @createTime 2023-01-25
 */
public class AuthInfo {
    private Integer userId;
    private String userName;

    private String authMsg;

    public AuthInfo(Integer userId, String userName, String authMsg) {
        this.userId = userId;
        this.userName = userName;
        this.authMsg = authMsg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthMsg() {
        return authMsg;
    }

    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
    }
}
