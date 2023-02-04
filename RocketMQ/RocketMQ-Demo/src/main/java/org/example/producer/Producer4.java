package org.example.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByMachineRoom;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByRandom;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 生产者
 * 直接通过queue选择器往queue中发送消息
 * @Author: By-BroDog
 * @CreateTime: 2022-11-26
 */
public class Producer4 {
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

//        orderSend(topic, producer);

        batchSend2Queues(topic, producer);

        // 关闭生产者 如果不关闭 进程会一直存在 默认是会一直建立长连接
        producer.shutdown();
        System.out.println("============  消息投递完成  ============");
    }

    private static void orderSend(String topic, DefaultMQProducer producer) throws Exception {
        Integer arg = 0;

        // 简单的queue选择器
        MessageQueueSelector messageQueueSelector = new MessageQueueSelector() {
            /**
             * 选择具体需要发送的queue
             * @param list      在当前的topic下所有的queue集合
             * @param message   消息
             * @param o         参数 对应send()中的arg参数
             * @return 选择的queue
             */
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                for (MessageQueue messageQueue : list) {
                    System.out.println(messageQueue.toString());
                }
                return list.get(arg);
            }
        };
        /**
         * 通过获取 send()中的arg这个参数，拿到它的hashCode，用hashCode与当前topic下的queue的长度进行取余 拿到下标
         * 然后选择当前下标的queue
         * 一般对于使用此方法时，arg相同就会选择到相同的queue
         */
        SelectMessageQueueByHash selectMessageQueueByHash = new SelectMessageQueueByHash();

        /**
         * 拿到队列的个数，然后取一个0~个数的随机数，来选择具体使用哪一个queue
         */
        SelectMessageQueueByRandom selectMessageQueueByRandom = new SelectMessageQueueByRandom();

        /**
         * 通过机房选择具体的queue rocketMq开源版此方法并没有具体的实现，需要自己手写具体的选择逻辑
         */
        SelectMessageQueueByMachineRoom selectMessageQueueByMachineRoom = new SelectMessageQueueByMachineRoom();


        for (int i = 0; i < 20; i++) {
            String body = "{\"id\": " + i + ", \"name\": \"张三\"}";
            Message message = new Message(topic,"TAG-A","KEY-A", body.getBytes());
            SendResult sendResult = producer.send(message, messageQueueSelector, arg, 2000);

            // 发送完成后 返回发送结果
            System.out.println("sendResult: " + sendResult.toString());
        }
    }

    private static void batchSend2Queues(String topic, DefaultMQProducer producer) throws Exception {
        orderSend1(topic, producer);
//        orderSend2(topic, producer);
//        orderSend3(topic, producer);
//        orderSend4(topic, producer);
    }


    private static void orderSend1(String topic, DefaultMQProducer producer) throws Exception {
        Integer arg = 0;

        // 简单的queue选择器
        MessageQueueSelector messageQueueSelector = new MessageQueueSelector() {
            /**
             * 选择具体需要发送的queue
             * @param list      在当前的topic下所有的queue集合
             * @param message   消息
             * @param o         参数 对应send()中的arg参数
             * @return 选择的queue
             */
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                return list.get(arg);
            }
        };
        for (int i = 0; i < 10000; i++) {
            String body = "{\"id\": 1-" + i + ", \"name\": \"张三\"}";
            Message message = new Message(topic,"TAG-A","KEY-A", body.getBytes());
            SendResult sendResult = producer.send(message, messageQueueSelector, arg, 2000);

            // 发送完成后 返回发送结果
            System.out.println("sendResult: " + sendResult.toString());
        }
    }

    private static void orderSend2(String topic, DefaultMQProducer producer) throws Exception {
        Integer arg = 1;

        // 简单的queue选择器
        MessageQueueSelector messageQueueSelector = new MessageQueueSelector() {
            /**
             * 选择具体需要发送的queue
             * @param list      在当前的topic下所有的queue集合
             * @param message   消息
             * @param o         参数 对应send()中的arg参数
             * @return 选择的queue
             */
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                return list.get(arg);
            }
        };
        for (int i = 0; i < 20; i++) {
            String body = "{\"id\": 2-" + i + ", \"name\": \"张三\"}";
            Message message = new Message(topic,"TAG-A","KEY-A", body.getBytes());
            SendResult sendResult = producer.send(message, messageQueueSelector, arg, 2000);

            // 发送完成后 返回发送结果
            System.out.println("sendResult: " + sendResult.toString());
        }
    }

    private static void orderSend3(String topic, DefaultMQProducer producer) throws Exception {
        Integer arg = 2;

        // 简单的queue选择器
        MessageQueueSelector messageQueueSelector = new MessageQueueSelector() {
            /**
             * 选择具体需要发送的queue
             * @param list      在当前的topic下所有的queue集合
             * @param message   消息
             * @param o         参数 对应send()中的arg参数
             * @return 选择的queue
             */
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                return list.get(arg);
            }
        };
        for (int i = 0; i < 20; i++) {
            String body = "{\"id\": 3-" + i + ", \"name\": \"张三\"}";
            Message message = new Message(topic,"TAG-A","KEY-A", body.getBytes());
            SendResult sendResult = producer.send(message, messageQueueSelector, arg, 2000);

            // 发送完成后 返回发送结果
            System.out.println("sendResult: " + sendResult.toString());
        }
    }

    private static void orderSend4(String topic, DefaultMQProducer producer) throws Exception {
        Integer arg = 3;

        // 简单的queue选择器
        MessageQueueSelector messageQueueSelector = new MessageQueueSelector() {
            /**
             * 选择具体需要发送的queue
             * @param list      在当前的topic下所有的queue集合
             * @param message   消息
             * @param o         参数 对应send()中的arg参数
             * @return 选择的queue
             */
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                return list.get(arg);
            }
        };
        for (int i = 0; i < 20; i++) {
            String body = "{\"id\": 4-" + i + ", \"name\": \"张三\"}";
            Message message = new Message(topic,"TAG-A","KEY-A", body.getBytes());
            SendResult sendResult = producer.send(message, messageQueueSelector, arg, 2000);

            // 发送完成后 返回发送结果
            System.out.println("sendResult: " + sendResult.toString());
        }
    }
}
