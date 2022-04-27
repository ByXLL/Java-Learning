package com.brodog.rabbitmq.helloworld;

import com.brodog.rabbitmq.utils.RabbitConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Producer {
    public static void main(String[] args) throws Exception {

        // 设置连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        // 获取tcp长连接
        Connection connection = connectionFactory.newConnection();

        // 创建通信信道
        Channel channel = connection.createChannel();

        /**
         * 创建队列，如果队列存在则直接使用
         * arg1     队列名称
         * arg2     是否持久化   false       MQ停掉数据就会丢失
         * arg3     是否队列私有化     false   所有消费者都可以访问      true    只有第一次拥有它的消费者可以消费
         * arg4     是否自动删除      false    代表连接停掉后不自动删除当前队列
         * argg5    额外参数
         */
        channel.queueDeclare(RabbitConstant.QUEUE_HELLO_WORLD,false,false,false,null);

        String msg = "狗哥6666";

        /**
         * 发送消息
         * arg1     交换机名字
         * arg2     队列名称
         * arg3     额外的设置属性
         * arg4     传递的内容的字节数组
         */
        channel.basicPublish("",RabbitConstant.QUEUE_HELLO_WORLD,null,msg.getBytes());

        // 关闭信道
        channel.close();
        // 关闭连接
        connection.close();

        System.out.println("消息发送成功");
    }
}
