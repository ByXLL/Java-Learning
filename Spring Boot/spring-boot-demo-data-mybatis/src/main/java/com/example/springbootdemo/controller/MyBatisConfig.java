package com.example.springbootdemo.controller;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@org.springframework.context.annotation.Configuration
public class MyBatisConfig {
    // 开启mybatis中的驼峰
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        // new 一个内部类
        return new ConfigurationCustomizer(){
            // 重写接口方法
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
