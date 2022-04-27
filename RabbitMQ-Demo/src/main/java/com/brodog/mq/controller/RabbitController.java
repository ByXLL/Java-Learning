package com.brodog.mq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author By-Lin
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String direct(@RequestParam String key) {
        String sendMsg = "key("+ key +"),exchange(direct)-"+System.currentTimeMillis();
        System.out.println("生产者发送： " + sendMsg);

        // 指定交换机、路由键、消息本体
        rabbitTemplate.convertAndSend("DirectExchange",key,sendMsg);
        return "生产者发送消息成功 "+ sendMsg;
    }


    @GetMapping("/topic")
    public String topic(@RequestParam String key) {
        String sendMsg = "key("+ key +"),exchange(topic)-"+System.currentTimeMillis();
        System.out.println("topic发送： " + sendMsg);

        // 指定交换机、路由键、消息本体
        rabbitTemplate.convertAndSend("TopicExchange",key,sendMsg);
        return "topic发送消息成功 "+ sendMsg;
    }

    @GetMapping("/fanout")
    public String fanout(@RequestParam String key) {
        String sendMsg = "key("+ key +"),exchange(fanout)-"+System.currentTimeMillis();
        System.out.println("fanout发送： " + sendMsg);

        // 指定交换机、路由键、消息本体
        rabbitTemplate.convertAndSend("FanoutExchange",key,sendMsg);
        return "fanout发送消息成功 "+ sendMsg;
    }
}
