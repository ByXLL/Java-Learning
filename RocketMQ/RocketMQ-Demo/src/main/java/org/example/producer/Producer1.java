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
public class Producer1 {
    public static void main(String[] args) throws Exception {
        /**
         * 1、在往broker中发消息之前，需要从nameServer中获取broker信息
         * 2、拿到broker信息后，才能发送消息
         */
        String producerGroup = "testProducerGroup";
        String nameServerAdd = "127.0.0.1:9876";
        String topic = "testTopic";

        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);

        // 1、设置nameServer地址
        producer.setNamesrvAddr(nameServerAdd);

        // 2、启动生产者
        producer.start();

        simpleSend(topic, producer);
//        sendBatch(topic, producer);
//        asyncSend(topic, producer);
    }

    private static void simpleSend(String topic, DefaultMQProducer producer) throws Exception {
        // 3、发送消息
        // 3、1 声明消息的topic，实际上就是等于确定你这条消息要发给谁
        String body = "{\"id\": \"1\", \"name\": \"张三\"}";
        Message message = new Message(topic,"TAG-A","KEY-A", body.getBytes());

        // 同步消息发送 会等待broker返回执行结果 否则会一直阻塞住 不会往下走
        SendResult sendResult = producer.send(message);

        // 发送完成后 返回发送结果
        System.out.println("sendResult: " + sendResult.toString());

        // 关闭生产者 如果不关闭 进程会一直存在 默认是会一直建立长连接
        producer.shutdown();
        System.out.println("============  消息投递完成  ============");
    }

    private static void sendBatch(String topic, DefaultMQProducer producer) throws Exception {
        List<Message> messageList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String body = "{\"id\": "+ i+1 +", \"name\": \"张三\"}";
            Message message = new Message(topic, body.getBytes());
            messageList.add(message);
        }
        SendResult sendResult = producer.send(messageList);
        System.out.println("batchSendResult: " + sendResult.toString());

        producer.shutdown();
        System.out.println("============  消息投递完成  ============");
    }

    private static void asyncSend(String topic, DefaultMQProducer producer) throws Exception {
        String body = "{\"id\": \"1\", \"name\": \"张三\",\"type\":\"async\"}";
        Message message = new Message(topic, body.getBytes());
        // 基于事件监听的机制 异步发送消息 当.send()之后不会一直进行阻塞，会根据执行结果走到对于的回调方法中
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("======= 发送消息成功回调  ======");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("=======  发送消息失败回调  ======");
                throwable.printStackTrace();
            }
        });

        // 注意 当使用了异步的方式的时候 不能在此处 shutdown, 否则在回调中，当响应结果回来的时候发现连接已经断开了，此时就会抛出网络异常
//        producer.shutdown();
        System.out.println("============  消息投递完成  ============");
    }
}
