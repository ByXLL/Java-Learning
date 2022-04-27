package com.brodog.rabbitmq.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 重写 消费者
 * @author By-Lin
 */
public class Receiver extends DefaultConsumer {

    private Channel channel;

    /**
     * 重写构造函数，信道通过外部进行传入
     */
    public Receiver(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("消费者接收到的信息是： " + msg);

        System.out.println("消息的TagID： " + envelope.getDeliveryTag());

        // false 只确认签收当前的消息，设置为 true的时候则代表签收该消费者所有未签收的消息
        channel.basicAck(envelope.getDeliveryTag(),false);


    }
}
