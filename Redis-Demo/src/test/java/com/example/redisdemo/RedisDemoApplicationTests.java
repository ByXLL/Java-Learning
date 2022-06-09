package com.example.redisdemo;

import com.example.redisdemo.entity.Goods;
import com.example.redisdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@SuppressWarnings("all")
class RedisDemoApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
//        redisTemplate.boundValueOps("dsadasdasdas").set("eqweqtwejijkjdfs");
//        redisTemplate.boundValueOps("lklfgikfdmkg").set("qqqqqqq");
//        redisTemplate.boundValueOps("rpiewim").set("fsfsdsdf");
//        redisTemplate.boundValueOps("fdsijoljlerltjlr").set("wwwwwwwwwwww");
//        redisTemplate.boundValueOps("rewrtew").set("ytyertre");

        // --------------------------------------  String  ------------------------

        /**
         * 使用场景
         *
         */

//        // 简单存入 字符串键值对  插入多个需要 自己手动循环执行
//        redisTemplate.boundValueOps("userName").set("张三");
//        // 设置超时时间
//        redisTemplate.boundValueOps("userName").set("李四", 60, TimeUnit.SECONDS);
//        // setnx 设置不存在的字符串 true 设置成功 false 设置失败
//        redisTemplate.boundValueOps("UserLock").setIfAbsent("张三");
//        // 删除
//        Boolean delete = redisTemplate.delete("userName");
//        // 累加 原子操作 没有的这个key的时候 会默认初始化为0
//        redisTemplate.boundValueOps("count").increment();
//        // 累减 返回结果
//        redisTemplate.boundValueOps("count").decrement();


        // 存储验证码 基于一个大的标识，
        redisTemplate.opsForValue().set("message-139777777777","6501",1,TimeUnit.MINUTES);


        User userInfo = new User(1, "张三", 20);

        redisTemplate.opsForValue().set("userData",userInfo);
//        redisTemplate.boundHashOps("UserList").put("1",userInfo);

        // 存储用户 购物车信息  key 是购物车标识 + 用户标识 hashkey 是商品id  value是个数
        redisTemplate.opsForHash().put("Cart-100","100",10);

        // 保存视频查看用户记录
        redisTemplate.opsForHash().put("Video-History-1","1",userInfo);

        // 登录信息 key 是token标识 + 用户id     value是token值
        redisTemplate.opsForValue().set("UserToken-100","xdadarwqwrqwrqwrqw");

        // 热榜信息 缓存热榜商品信息 用于保存相同类型的数据
        ArrayList<Goods> arrayList = new ArrayList(100);
        for (int i = 1; i <= 50; i++) {
            arrayList.add(new Goods(i,"商品名称："+i, BigDecimal.valueOf(i+100)));
        }
        redisTemplate.opsForList().rightPushAll("HotGoods",arrayList);


        redisTemplate.opsForList().leftPop("HotGoods");
        redisTemplate.opsForList().rightPush("HotGoods",new Goods(1000,"华为手机",new BigDecimal(1000)));
//        redisTemplate.exec();

        List list = redisTemplate.opsForList().range("HotGoods", 0, 100);
        System.out.println(list.get(0).toString());

    }

}
