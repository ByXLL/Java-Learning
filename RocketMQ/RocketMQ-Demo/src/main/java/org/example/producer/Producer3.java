package org.example.producer;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 生产者
 * @Author: By-BroDog
 * @CreateTime: 2022-11-26
 */
public class Producer3 {
    public static void main(String[] args) throws Exception {
        String producerGroup = "testProducerGroup3";
        String nameServerAdd = "127.0.0.1:9876";
        String topic = "testTopic";

        // 构建事务生产者
        TransactionMQProducer producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr(nameServerAdd);

        // 设置事务回调
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                // 在此方法中执行本地事务
                System.out.println("==========  executeLocalTransaction  =============");
                String messageBody = new String(message.getBody());
                String transactionId = message.getTransactionId();
                System.out.println("msg: " + messageBody);
                System.out.println("transactionId: " + transactionId);

                // todo 处理本地事务业务逻辑
//                try {
//                    // 方法1
//                    // 方法2
//                    // 方法3
//                }catch (Exception e) {
//                    return LocalTransactionState.ROLLBACK_MESSAGE;
//                }
//                return LocalTransactionState.COMMIT_MESSAGE;
                /**
                 * 根据事务的执行结果  返回HalfMessage具体的业务状态值 COMMIT_MESSAGE || ROLLBACK_MESSAGE || UNKNOW
                 * 当是  COMMIT 或者是 ROLLBACK就会直接返回给broker告诉当前的事务消息的状态
                 * 就不会触发定时任务就不会触发 checkLocalTransaction回调
                 */
                return LocalTransactionState.UNKNOW;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                // 回调检查本地事务的状态 broker定时器查询的回调
                System.out.println("=============  checkLocalTransaction  ==============");
                String messageBody = new String(messageExt.getBody());
                String transactionId = messageExt.getTransactionId();
                System.out.println("===msg: " + messageBody);
                System.out.println("===transactionId: " + transactionId);
                /**
                 * 根据状态  返回HalfMessage具体的业务状态值 COMMIT_MESSAGE || ROLLBACK_MESSAGE || UNKNOW
                 * 当是  COMMIT 或者是 ROLLBACK就会直接返回给broker告诉当前的事务消息的状态
                 * 就不会触发定时任务就不会再触发 checkLocalTransaction回调
                 *
                 * 注意：在此回调中，broker会获取所有的处于 UNKNOW 状态的消息触发这个回调，
                 * 所以之前未处理的消息也会重新出现在检查他的事务的执行结果
                 */
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        producer.start();
        sendTransactionMessage(topic, producer);
    }

    private static void sendTransactionMessage(String topic, DefaultMQProducer producer) throws Exception {

            String body = "{\"id\": " + 100 + ", \"name\": \"张三\"}";
            Message message = new Message(topic,"TAG-Tran","KEY-A", body.getBytes());

            SendResult sendResult = producer.sendMessageInTransaction(message, null);

            // 发送完成后 返回发送结果
            System.out.println("sendResult: " + sendResult.toString());


        // 关闭生产者 如果不关闭 进程会一直存在 默认是会一直建立长连接
//        producer.shutdown();
        System.out.println("============  消息投递完成  ============");
    }
}
