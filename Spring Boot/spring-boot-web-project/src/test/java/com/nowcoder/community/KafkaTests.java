package com.nowcoder.community;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

/**
 * kafka 测试类
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class KafkaTests {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void testKafka() {
        // 生产者 发送消息
        kafkaProducer.sendMessage("test","你好");
        kafkaProducer.sendMessage("test","在吗");

        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 生产者类
 */
@Component
class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息
     * @param topic     消息主题
     * @param content   消息内容
     */
    public void sendMessage(String topic, String content) {
        kafkaTemplate.send(topic,content);
    }
}
/**
 * 消费者类
 */
@Component
class KafkaConsumer {
    // 注解 监听主题 只要服务起来了 自动一直监听 监听到了 将ConsumerRecord 回传到方法中
    @KafkaListener(topics = {"test"})
    public void handleMessage(ConsumerRecord record) {
        System.out.println("监听到了；"+record.value());
    }
}