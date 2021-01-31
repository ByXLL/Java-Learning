package com.nowcoder.community.config;

import com.nowcoder.community.controller.interceptor.AlphaInterceptor;
import com.nowcoder.community.controller.interceptor.LoginRequiredInterceptor;
import com.nowcoder.community.controller.interceptor.LoginTicketInterceptor;
import com.nowcoder.community.controller.interceptor.MessageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc 的配置类  去实现 WebMvcController
 */
@Configuration
public class WebMvcController implements WebMvcConfigurer {

    // 将我们声明的过滤器注入进来
    private final AlphaInterceptor alphaInterceptor;

    // 注入登录校验拦截器
    private final LoginTicketInterceptor loginTicketInterceptor;

    // 注入鉴权拦截器
    private final LoginRequiredInterceptor loginRequiredInterceptor;

    // 注入消息拦截器
    private final MessageInterceptor messageInterceptor;

    public WebMvcController(AlphaInterceptor alphaInterceptor, LoginTicketInterceptor loginTicketInterceptor,
                            LoginRequiredInterceptor loginRequiredInterceptor, MessageInterceptor messageInterceptor) {
        this.alphaInterceptor = alphaInterceptor;
        this.loginTicketInterceptor = loginTicketInterceptor;
        this.loginRequiredInterceptor = loginRequiredInterceptor;
        this.messageInterceptor = messageInterceptor;
    }


    // 重写 注册拦截器方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 通过传进来的 registry 去注册 Interceptor
        // 调用 addInterceptor 去添加一个拦截器
        registry.addInterceptor(alphaInterceptor).excludePathPatterns(
            // 设置白名单
            "/*/*.css","/*/*.js","/*/*.jpg","/*/*.png","/*/*.jpeg"
        ).addPathPatterns(
            // 明确拦截的路径
            "/register","/login"
        );

        // 新增一个获取用户信息的拦截器
        registry.addInterceptor(loginTicketInterceptor).excludePathPatterns(
            "/*/*.css","/*/*.js","/*/*.jpg","/*/*.png","/*/*.jpeg"
        );

        // 新增一个鉴权的拦截器
        registry.addInterceptor(loginRequiredInterceptor).excludePathPatterns(
                "/*/*.css","/*/*.js","/*/*.jpg","/*/*.png","/*/*.jpeg"
        );

        // 新增一个消息的拦截器
        registry.addInterceptor(messageInterceptor).excludePathPatterns(
                "/*/*.css","/*/*.js","/*/*.jpg","/*/*.png","/*/*.jpeg"
        );
    }
}
