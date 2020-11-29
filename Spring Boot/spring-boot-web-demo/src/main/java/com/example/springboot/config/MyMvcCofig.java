package com.example.springboot.config;

import com.example.springboot.compotent.LoginHandlerInterceptor;
import com.example.springboot.compotent.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc
@Configuration
public class MyMvcCofig implements WebMvcConfigurer  {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        // 创建视图映射规则  默认映射的都会去 templates 下找
////        registry.addViewController("/").setViewName("success");
//    }

    // 因为所有的 WebMvcConfigurer 组件会一起起作用  专门写一个方法 用于返回 WebMvcConfigurer
    @Bean  // 将组件注册到容器中
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 添加我们定义的登录拦截器  拦截全部请求  并排除登录页的请求
                // springBoot 对静态资源做了处理，不会影响静态资源的访问
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/login","/user/login");
            }

            // ctrl + o
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return webMvcConfigurer;
    }

    // 使用我们自己定义的通过 获取url来切换国际化信息
    @Bean   // 将组件注册到容器中
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }

}
