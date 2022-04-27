package com.brodog.mq.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 自定义消费者
 * 手动消费队列中的消息 并在消费完成后，才去手动确认消息投递完全成功
 * @author By-Lin
 */
@Component
public class Receiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        // 当进入到这个方法，代表监听到了这个消息 与 @RabbitListener 和 @RabbitHandler 一样
        try{
            String msg = new String(message.getBody());
            System.out.println("手动消费者----接收到了消息-----" + msg);
            // do something
            // 当业务处理完成了 手动提交 ack 返回 完成整个mq的状态
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("手动消费者消费完成-------------");
        }catch (Exception e) {
            // 当业务出现异常
            System.out.println("手动消费处理的时候发生异常------");
            // 将消息设置为消费失败 返回失败的ack
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            System.out.println("手动消费处理的时候发生异常------并返回 nack");
        }
    }
}
