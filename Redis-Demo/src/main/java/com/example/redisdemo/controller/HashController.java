package com.example.redisdemo.controller;

import com.example.redisdemo.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author By-Lin
 */
@RestController
@RequestMapping("/redis")
public class HashController {
    private final RedisTemplate redisTemplate;

    public HashController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/add/hash")
    public Object addHash(@RequestParam("hashKey") String hashKey, @RequestParam("key") String key, @RequestParam("value") String value) {
//        redisTemplate.boundHashOps(hashKey).put(key,value);
        User userInfo = new User(1, "张三", 20);

        redisTemplate.boundHashOps("UserList").put("1",userInfo);
        return "";

    }

    @RequestMapping("/get/hash")
    public Object getHash(@PathParam("key") String key) {
        return redisTemplate.boundHashOps("UserList").get("1");
    }
}
