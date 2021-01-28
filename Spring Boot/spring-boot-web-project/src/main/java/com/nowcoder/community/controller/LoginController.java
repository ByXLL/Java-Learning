package com.nowcoder.community.controller;

import com.google.code.kaptcha.Producer;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.RedisKeyUtil;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController implements CommunityConstant {

    // 声明日志对象
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final RedisTemplate redisTemplate;
    private final UserService userService;
    private final Producer producer;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public LoginController(RedisTemplate redisTemplate, UserService userService, Producer producer) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
        this.producer = producer;
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "/site/register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, User user) {
        Map<String,Object> map = userService.register(user);
        if(map == null || map.isEmpty()) {
            // 向页面注入提示
            model.addAttribute("msg","注册成功，请完成激活");
            // 向页面注入 需要跳转的路径
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else {
            // 将返回注册失败的结果返回回来 注入到页面
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/register";
        }
    }

    // 访问激活页
    @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code){
        // 接收用户的激活状态
        int result = userService.activation(userId,code);
        if(result == ACTIVATION_SUCCESS) {
            // 向页面注入提示
            model.addAttribute("msg","激活成功,请前往登录");
            // 向页面注入 需要跳转的路径
            model.addAttribute("target","/login");
        }else if(result == ACTIVATION_REPEAT) {
            // 向页面注入提示
            model.addAttribute("msg","当前账号已经激活了");
            // 向页面注入 需要跳转的路径
            model.addAttribute("target","/index");
        }else {
            // 向页面注入提示
            model.addAttribute("msg","激活失败，请检查您的激活码");
            // 向页面注入 需要跳转的路径
            model.addAttribute("target","/index");
        }
        // 跳转提示页
        return "/site/operate-result";
    }

    // 访问登录页
    @GetMapping("/login")
    public String getLoginPage() {
        return "/site/login";
    }

    // 登录操作
    @PostMapping("/login")
    public String login(Model model, HttpSession session, HttpServletResponse response,
                        String username, String password, String code, Boolean rememberme,
                        @CookieValue("kaptchaOwner") String kaptchaOwner) {
//        // 先校验验证码 session
//        String kaptcha = (String) session.getAttribute("kaptcha");

        // 校验验证码 redis
        String kaptcha = null;
        // 判断是否为空
        if(StringUtils.isNoneBlank(kaptchaOwner)) {
            // 构造redis中的key
            String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
            kaptcha = (String) redisTemplate.opsForValue().get(redisKey);
        }


        // equalsIgnoreCase 忽略大小写
        if(StringUtils.isBlank(kaptcha) || StringUtils.isBlank(code) || !kaptcha.equalsIgnoreCase(code)) {
            model.addAttribute("codeMsg","验证码错误");
            return "/site/login";
        }

        // 根据是否勾选记住我 动态改变 登录凭证 生效时间
        if(rememberme == null ) { rememberme = false ;}
        int expiredSeconds = rememberme ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;

        // 检查账号密码
        Map<String,Object> map = userService.login(username,password,expiredSeconds);
        // 如果 map 对象中包含 ticket 则为登陆成功
        if(map.containsKey("ticket")) {
            // 实例化一个 Cookie 携带 ticket 返回给客户端
            Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
            // 设置生效的路径
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSeconds);

            // 将cookie 注入到响应头中
            response.addCookie(cookie);
            // 登录成功 重定向到首页
            return "redirect:/index";
        }else {
            // 接收 service 层返回的错误信息  并将错误信息 注入给页面中
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/login";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/login";
    }

    // 获取验证码
    @GetMapping("/kaptcha")
    private void getKaptcha(HttpServletResponse response, HttpSession session) {
        // 生成验证码 文字
        String text =  producer.createText();
        // 生成验证码 图片 将生成的文字传入
        BufferedImage image = producer.createImage(text);

//        // 将验证码存入session
//        session.setAttribute("kaptcha",text);


        // 验证码的归属
        String kaptchaOwner = CommunityUtil.generateUUID();
        Cookie cookie = new Cookie("kaptchaOwner",kaptchaOwner);
        cookie.setMaxAge(60);
        cookie.setPath(contextPath);
        response.addCookie(cookie);
        // 将验证码存入redis
        String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
        redisTemplate.opsForValue().set(redisKey,text,60, TimeUnit.SECONDS);

        // 将图片输出给浏览器
        response.setContentType("image/png");
        // 从响应中获取 输出流
        try {
            OutputStream os = response.getOutputStream();
            // 声明使用 ImageIO 流输出 （输出内容，格式，使用的流）
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            logger.error("响应验证码失败:"+e.getMessage());
        }

    }
}
