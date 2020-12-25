package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService implements CommunityConstant {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    /***
     * 根据用户id 获取用户信息
     * @param userId    用户id
     * @return  用户信息
     */
    public User findUserById(int userId) {
        return userMapper.selectById(userId);
    }

    /**
     * 注册用户
     * @param user  用户对象
     * @return  注册结果
     */
    public Map<String,Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if(user == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        if(StringUtils.isBlank(user.getEmail())) {
             map.put("emailMsg","邮箱不能为空");
             return map;
        }
        if(StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg","密码不能为空");
            return map;
        }

        // 验证账号
        User u = userMapper.selectByEmail(user.getEmail());
        if(u != null) {
            map.put("emailMsg","该邮箱已被注册");
            return map;
        }
        u = userMapper.selectByName(user.getUsername());
        if(u != null) {
            map.put("usernameMsg","用户已存在");
            return map;
        }

        // 注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));      // 生成随机数 加入加密
        user.setPassword(CommunityUtil.md5(user.getPassword()) + user.getSalt());
        user.setStatus(0);
        user.setType(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));    // 生成随机整数拼接到url
        user.setCreateTime(new Date());
        // 插入用户
        userMapper.insertUser(user);

        // 处理激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // 处理需要返回的激活地址
        String url = domain + contextPath + "/activation" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url",url);

        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }

    /**
     * 激活用户
     * @param userId    用户id
     * @param code  激活码
     * @return      状态值
     */
    public int activation(int userId, String code) {
        // 先通过用户id 获取用户信息
        User user = userMapper.selectById(userId);
        // 判断当前用户是否 已经激活
        if(user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        }else if(user.getActivationCode().equals(code)){
            // 当激活码和数据库的相同  则更新状态
            userMapper.updateStatus(userId,1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }

}
