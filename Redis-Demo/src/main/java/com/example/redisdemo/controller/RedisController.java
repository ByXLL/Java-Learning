package com.example.redisdemo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * @author By-Lin
 */
@RestController
@RequestMapping("/redis/string")
public class RedisController {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("add")
    public Object addString(@PathParam("key") String key, @PathParam("value") String value) {

        return "";
    }
}

