package com.example.demo.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 订单请求控制器
 * @author By-Lin
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final StringRedisTemplate stringRedisTemplate;

    public OrderController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostMapping("/add")
    public String addOrder() {
        // 模拟商品id
        long goodsId = 1L;

        String msg = "";

        // 使用uuid 设置给value，用于标记每一个线程，
        String requestFlag = UUID.randomUUID().toString();

        //region 处理死锁问题， 使用 try / finally 去处理当业务逻辑报错，造成的无法解锁问题
        try{
            // region 加锁
            // 如果加锁成功，则说明当前请求能够进来，走到后面的减库存环节
            // 这里设置超时时间，解决物理掉线，代码无法走到finally造成死锁
            Boolean lockState = stringRedisTemplate.opsForValue().setIfAbsent(String.valueOf(goodsId),requestFlag,3, TimeUnit.SECONDS);
            if(!lockState) { return "糟糕，你没有抢到";}
            // endregion


            // 读取redis中订单库存数量，模拟真实场景库存
            int stock = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("stock")));

            // 减库存操作
            if(stock>0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",String.valueOf(realStock));
                System.out.println("下单成功，当前库存：" + realStock);
                msg = "下单成功";
            }else {
                msg =  "下单失败，商品已售空！";
            }
        }finally {
            //region 释放锁
            // 在解锁的时候判断当前要删除的锁的是不是当前线程的，解决逻辑代码执行时间超过锁的自动超时时间，第二个线程涌入，存在误删锁的问题
            if(requestFlag.equals(stringRedisTemplate.opsForValue().get(String.valueOf(goodsId)))) {
                stringRedisTemplate.delete(String.valueOf(goodsId));
            }
            //endregion
        }
        //endregion

        return msg;
    }
}
