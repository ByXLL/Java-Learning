package com.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 生产者
 * @author By-Lin
 */
@SuppressWarnings("all")
public class MyProducer {

    private final static String TOPIC_NAME = "order-topic";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();

        // 声明集群服务器，但是基于zookeeper，只写一个的话，Kafka还是会索引到对应的集群服务器的，只要保证填写的这个可达
        props.put("bootstrap.servers", "192.168.100.110:9092");

        /**
         * 发出消息持久化机制参数
         *（1）acks=0： 表示producer不需要等待任何broker确认收到消息的回复，就可以继续发送下一条消息。性能最高，但是最容易丢消息。
         *（2）acks=1： 至少要等待leader已经成功将数据写入本地log，但是不需要等待所有follower是否成功写入。就可以继续发送下一条消息。
         *  这种情况下，如果follower没有成功备份数据，而此时leader又挂掉，则消息会丢失。
         *（3）acks=-1或all： 需要等待 min.insync.replicas(默认为1，推荐配置大于等于2) 这个参数配置的副本个数都成功写入日志，
         * 这种策略会保证只要有一个备份存活就不会丢失数据。这是最强的数据保证。一般除非是金融级别，或跟钱打交道的场景才会使用这种配置。
        */
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        /**
         * 发送失败会重试，默认重试间隔100ms，重试能保证消息发送的可靠性，但是也可能造成消息重复发送，
         * 比如网络抖动，所以需要在接收者那边做好消息接收的幂等性处理
        */
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        // 重试间隔设置 单位 ms
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1);

        // 设置发送消息的本地缓冲区，如果设置了该缓冲区，消息会先发送到本地缓冲区，可以提高消息发送性能，默认值是33554432，即32MB
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        /**
         * kafka本地线程会从缓冲区取数据，批量发送到broker，
         * 设置批量发送消息的大小，默认值是16384，即16kb，就是说一个batch满了16kb就发送出去
        */
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

        /**
         * 默认值是0，意思就是消息必须立即被发送，但这样会影响性能一般设置10毫秒左右，
         * 就是说这个消息发送完后会进入本地的一个batch，如果10毫秒内，这个batch满了16kb就会随batch一起被发送出去
         * 如果10毫秒内，batch没满，那么也必须把消息发送出去，不能让消息的发送延迟时间太长
        */
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);

        // 把发送的key从字符串序列化为字节数组
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 把发送消息value从字符串序列化为字节数组
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 构建消息生产者
        Producer<String, String> producer = new KafkaProducer<>(props);

        int msgNum = 10;
        // 用于记录发送成功的个数
        final CountDownLatch countDownLatch = new CountDownLatch(msgNum);
        for (int i = 1; i <= msgNum; i++) {
            Order order = new Order(i, 100+i, "订单----" + i);
            // 创建消息载体 指定发送分区
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, 0, order.getId() + "", JSON.toJSONString(order));

            // 创建消息载体 未指定发送分区，具体发送的分区计算公式：hash(key)%partitionNum
            ProducerRecord<String, String> producerRecord2 = new ProducerRecord<>(TOPIC_NAME, order.getId() + "", JSON.toJSONString(order));

            // 普通方式发送
//            producer.send(producerRecord);

            // 同步阻塞等待发送结果 等待消息发送成功
            RecordMetadata metadata = producer.send(producerRecord).get();
            System.out.println("同步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-" + metadata.partition() + "|offset-" + metadata.offset());

            // 异步回调方式发送消息
//            producer.send(producerRecord, (metadata, exception)-> {
//                    if (exception != null) {
//                        System.err.println("发送消息失败：" + exception.getStackTrace());
//                    }
//                    if (metadata != null) {
//                        System.out.println("异步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-" + metadata.partition() + "|offset-" + metadata.offset());
//                    }
//                    // -1
//                    countDownLatch.countDown();
//                }
//            );
        }
        // 异步保证所有消息发送成功，否则会一直阻塞，等待完成再去关闭生产者
//        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.close();
    }
}
