package com.example.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.boundValueOps("dsadasdasdas").set("eqweqtwejijkjdfs");
        redisTemplate.boundValueOps("lklfgikfdmkg").set("qqqqqqq");
        redisTemplate.boundValueOps("rpiewim").set("fsfsdsdf");
        redisTemplate.boundValueOps("fdsijoljlerltjlr").set("wwwwwwwwwwww");
        redisTemplate.boundValueOps("rewrtew").set("ytyertre");
    }

}
