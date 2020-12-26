package com.nowcoder.community.controller;

import com.google.code.kaptcha.Producer;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class LoginController implements CommunityConstant {

    // 声明日志对象
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private Producer producer;

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

    // 获取验证码
    @GetMapping("/kaptcha")
    private void getKaptcha(HttpServletResponse response, HttpSession session) {
        // 生成验证码 文字
        String text =  producer.createText();
        // 生成验证码 图片 将生成的文字传入
        BufferedImage image = producer.createImage(text);
        // 将验证码存入session
        session.setAttribute("kaptcha",text);

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
