package com.brodog.principle;

import com.brodog.principle.EnableDefineService;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 使用自定义注解 开启bean 自动装配的方式
 * 在这里使用自定义的bean自定义注入注解，完成该bean的注入
 * @author By-Lin
 */
@EnableDefineService
@SpringBootApplication
public class BootStrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootStrap.class, args);
        UserService userService = context.getBean(UserService.class);
        userService.login();
    }
}
