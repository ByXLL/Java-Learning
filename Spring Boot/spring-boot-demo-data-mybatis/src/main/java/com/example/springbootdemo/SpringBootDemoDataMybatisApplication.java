package com.example.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.example.springbootdemo.mapper")
@SpringBootApplication
public class SpringBootDemoDataMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoDataMybatisApplication.class, args);
    }

}
