package com.nowcoder.community.service;

import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import com.nowcoder.community.util.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserService implements CommunityConstant {
    private final UserMapper userMapper;

//    private LoginTicketMapper loginTicketMapper;

    private final RedisTemplate redisTemplate;

    private final MailClient mailClient;

    private final TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public UserService(UserMapper userMapper, MailClient mailClient, TemplateEngine templateEngine, RedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.mailClient = mailClient;
        this.templateEngine = templateEngine;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 根据用户id 获取用户信息
     * @param userId    用户id
     * @return  用户信息
     */
    public User findUserById(int userId) {
//        return userMapper.selectById(userId);
        // 使用 redis
        User user = getCache(userId);
        if(user == null) {
            user = initCache(userId);
        }
        return user;
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
            // 清空 redis中的缓存
            clearCache(userId);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }


    /**
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

        // 老式使用数据库保存登录凭证  不推荐使用
//        loginTicketMapper.insertLoginTicket(loginTicket);

        // 使用Redis保存登录凭证
        String redisKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());
        redisTemplate.opsForValue().set(redisKey,loginTicket);


        // 向页面控制器层登录凭证
        map.put("ticket",loginTicket.getTicket());
        return map;
    }

    /**
     * 退出 修改用户登录的凭证
     * @param ticket
     */
    public void logout(String ticket) {
//        loginTicketMapper.updateStatus(ticket, 1);
        // 使用Redis保存登录凭证
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) redisTemplate.opsForValue().get(ticket);
        loginTicket.setStatus(1);
        redisTemplate.opsForValue().set(redisKey,loginTicket);
    }

    /**
     * 获取登录凭证
     * @param ticket
     * @return
     */
    public LoginTicket findLoginTicket(String ticket) {
//        return  loginTicketMapper.selectByTicket(ticket);
        // 获取 Redis 保存的登录凭证
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        return (LoginTicket) redisTemplate.opsForValue().get(redisKey);
    }

    /**
     * 更新用户头像地址
     * @param userId    用户id
     * @param headerUrl     头像链接地址
     * @return
     */
    public int updateHeader(int userId, String headerUrl) {
        int rows =  userMapper.updateHeader(userId,headerUrl);
        // redis 清空缓存
        clearCache(userId);
        return rows;
    }

    /**
     * 通过名字名查询用户
     * @param userName  用户名
     * @return      用户对象
     */
    public User findUserByName(String userName) {
        return userMapper.selectByName(userName);
    }

    /**
     * 1.优先从redis中取值
     * @param userId    用户id
     * @return
     */
    private User getCache(int userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        return (User) redisTemplate.opsForValue().get(redisKey);
    }

    /**
     * 2.取不到时初始化缓存信息
     * @param userId    用户id
     * @return
     */
    private User initCache(int userId) {
        // 从mysql中获取数据
        User user = userMapper.selectById(userId);
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.opsForValue().set(redisKey,user,3600, TimeUnit.SECONDS);
        return user;
    }

    /**
     * 3.数据变更时清除缓存数据
     * @param userId   用户id
     */
    private void clearCache(int userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }
    /**
     * security 获取用户的权限
     * @param userId
     * @return
     */
    public Collection<? extends GrantedAuthority> getAuthorities(int userId) {
        User user = this.findUserById(userId);
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new GrantedAuthority() {
           // 判断 获取权限的字符串名
            @Override
            public String getAuthority() {
                switch (user.getType()) {
                    case 1:
                        return AUTHORITY_ADMIN;
                    case 2:
                        return AUTHORITY_MODERATOR;
                    default:
                        return AUTHORITY_USER;
                }
            }
        });
        return list;
    };
}
