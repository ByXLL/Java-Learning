package org.example.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消费者
 * @Author: By-BroDog
 * @CreateTime: 2022-11-26
 */
public class Consumer4 {
    public static void main(String[] args) throws Exception {
        String consumerGroup = "testConsumeGroup4";
        String nameServerAdd = "127.0.0.1:9876";
        // 使用push模式的消费者
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(consumerGroup);
        pushConsumer.setNamesrvAddr(nameServerAdd);
        // 每一个consumer只能关注一个topic 所以需要声明订阅哪一个生产者对于的topic
        String testTopic = "testTopic";

        // 可以使用 new 选择器指定tag 也可以直接在
        MessageSelector messageTagSelector = MessageSelector.byTag("TAG-A");

        // 声明关注的主题 可以通过设置过滤自获取某个TAG标识的消息 可通过 || 进行拼接多个 *代表获取所有的
        pushConsumer.subscribe(testTopic,  messageTagSelector);

        /**
         * 多线程消费
         * 当使用 MessageListenerConcurrently 监听器的时候，会进行并发消费，使用多线程进行消费
         *
         */
        MessageListenerConcurrently messageListenerConcurrently = new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                // todo 拿到消息 执行业务逻辑
                for (MessageExt ext : list) {
                    System.out.println("thread："+ Thread.currentThread().getName() + " in the topic: " + ext.getTopic() + " got the message: " + new String(ext.getBody()));
                }
                // 默认情况下 这条消息只会被一个消费者消费到
                // 此处的return是ack机制，就是告诉broker，当前消息成功消费了，这个时候在broker中就会将当前这条消息的状态值进行修改，就不会重新发送或者继续发给别人了
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        };

        /**
         * 顺序消费 单线程消费
         * 注意：默认这是针对于一个queue是只有一个线程进行消费，当存在有多个queue的时候，对每一个queue开启一个独立的线程
         * 当我们想哪怕存在多个queue消费的时候也是要顺序消费，需要设置 setConsumeThreadMin(1)
         *
         * 总结：当只使用 MessageListenerOrderly 并未设置 setConsumeThreadMin()时，会对每一个queue创建一个线程进行消费
         *  当使用了 MessageListenerOrderly 并设置 setConsumeThreadMin(1)时，无论存在多少个queue 都只会有一个线程进行消费
         *  当使用了 MessageListenerOrderly 并设置 setConsumeThreadMin(1)时，无论setConsumeThreadMax()为多少都只会有一个线程进行消费
         */
        pushConsumer.setConsumeThreadMax(10);
        pushConsumer.setConsumeThreadMin(1);
        MessageListenerOrderly messageListenerOrderly = new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt ext : list) {
                    System.out.println("thread："+ Thread.currentThread().getName() + " in the topic: " + ext.getTopic() + "queue: " + ext.getQueueId()  +  " got the message: " + new String(ext.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        };

        // 设置注册好的监听器
        pushConsumer.registerMessageListener(messageListenerConcurrently);
        // 设置消息模式 默认模式为集群消费模式  CLUSTERING（集群模式） BROADCASTING（广播模式）
        pushConsumer.setMessageModel(MessageModel.CLUSTERING);
        pushConsumer.start();
    }
}
