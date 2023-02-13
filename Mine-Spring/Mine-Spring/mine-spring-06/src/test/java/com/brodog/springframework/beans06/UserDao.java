package com.brodog.springframework.beans06;

import java.util.HashMap;
import java.util.Map;

/**
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class UserDao {
    private static Map<String, String> userInfoMap = new HashMap<>();
    static {
        userInfoMap.put("10001", "张三");
        userInfoMap.put("10002", "李四");
        userInfoMap.put("10003", "王五");
    }
    public void findUser(String userId) {
        System.out.println("查找用户成功：" + userInfoMap.get(userId));
    }
}
