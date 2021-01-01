package com.nowcoder.community.service;

import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.LoginTicket;
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
    private LoginTicketMapper loginTicketMapper;

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


    /***
     * 登录
     * @param username  用户名
     * @param password  密码
     * @param expiredSeconds    登录过期时间 /s
     * @return
     */
    public Map<String,Object> login(String username, String password,int expiredSeconds) {
        // 设置返回的数据格式 在controller层中通过 接收map 通过 .get(key) 获取对应返回的信息
        Map<String,Object> map = new HashMap<>();

        // 空值判断
        if(StringUtils.isBlank(username)) {
            map.put("usernameMsg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)) {
            map.put("passwordMsg","密码不能为空");
            return map;
        }

        // 验证账号
        User user = userMapper.selectByName(username);
        if(user == null) {
            map.put("usernameMsg","该用户不存在");
            return map;
        }
        if(user.getStatus() == 0) {
            map.put("usernameMsg","当前账号未激活");
            return map;
        }
        // 将明文密码加上加密字段 然后进行md5 加密 判断是否和数据库中保存的一样
        password = CommunityUtil.md5(password + user.getSalt());
        System.out.println("校验的数据----"+password);
        System.out.println("获取的数据-----"+user.getPassword());
        if(!user.getPassword().equals(password)) {
            map.put("passwordMsg","密码错误");
            return map;
        }


        // 登录成功 生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setStatus(0);
        loginTicket.setTicket(CommunityUtil.generateUUID());    // 生成随机 uuid 作为登录凭证信息
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));   // 设置有效时间 当前系统时间 + 传进来的偏移量

        loginTicketMapper.insertLoginTicket(loginTicket);

        // 向页面控制器层登录凭证
        map.put("ticket",loginTicket.getTicket());
        return map;
    }

    /**
     * 退出 修改用户登录的凭证
     * @param ticket
     */
    public void logout(String ticket) {
        loginTicketMapper.updateStatus(ticket, 1);
    }

    /**
     * 获取登录凭证
     * @param ticket
     * @return
     */
    public LoginTicket findLoginTicket(String ticket) {
        return  loginTicketMapper.selectByTicket(ticket);
    }
}
