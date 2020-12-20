package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /***
     * 根据用户id 获取用户信息
     * @param userId    用户id
     * @return  用户信息
     */
    public User findUserById(int userId) {
        return userMapper.selectById(userId);
    }

}
