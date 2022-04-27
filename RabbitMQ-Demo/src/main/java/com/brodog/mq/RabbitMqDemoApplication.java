package com.brodog.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author By-Lin
 */
@SpringBootApplication
public class RabbitMqDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqDemoApplication.class,args);
    }
}
