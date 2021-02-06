package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommunityApplication {
//    // 管理bean的初始化
//    @Deprecated
//    @PostConstruct
//    public void init() {
//        // 解决netty启动冲突的问题
//        // Netty4Utils 类中查看
//        System.setProperty("es.set.netty.runtime.available.processors","false");
//    }

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
