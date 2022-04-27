package com.brodog.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * mq消费者
 * 监听队列 queue1
 * 自动确认 消息投递成功
 * @author By-Lin
 */
//@Component
//@RabbitListener(queues = "queue1")
public class Consumer1 {

    @RabbitHandler
    public void process(String msg) {
        // 当执行到了这里，就将代表着这个消息处理成功了，但是如果在这个方法内部出现了异常呢，这个时候就需要开启手动确认
        System.out.println("queue1队列的消费者1-消费了消息-"+msg);
    }
}
