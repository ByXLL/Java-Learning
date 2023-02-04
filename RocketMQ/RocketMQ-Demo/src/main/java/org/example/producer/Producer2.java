package org.example.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者
 * @Author: By-BroDog
 * @CreateTime: 2022-11-26
 */
public class Producer2 {
    public static void main(String[] args) throws Exception {
        /**
         * 1、在往broker中发消息之前，需要从nameServer中获取broker信息
         * 2、拿到broker信息后，才能发送消息
         */
        String producerGroup = "testProducerGroup2";
        String nameServerAdd = "127.0.0.1:9876";
        String topic = "testTopic";

        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);

        // 1、设置nameServer地址
        producer.setNamesrvAddr(nameServerAdd);

        // 2、启动生产者
        producer.start();

        simpleSend(topic, producer);
    }

    private static void simpleSend(String topic, DefaultMQProducer producer) throws Exception {
        for (int i = 1; i <= 100 ; i++) {
            String body = "{\"id\": " + i + ", \"name\": \"张三\"}";
            Message message = new Message(topic,"TAG-A","KEY-A", body.getBytes());

            // 通过设置消息属性 可用于在超大量数据中 在消费者端通过 sql这种高度灵活的过滤方式精确获取数据
            message.putUserProperty("id", String.valueOf(i));

            SendResult sendResult = producer.send(message);

            // 发送完成后 返回发送结果
            System.out.println("sendResult: " + sendResult.toString());
        }


        // 关闭生产者 如果不关闭 进程会一直存在 默认是会一直建立长连接
        producer.shutdown();
        System.out.println("============  消息投递完成  ============");
    }
}
