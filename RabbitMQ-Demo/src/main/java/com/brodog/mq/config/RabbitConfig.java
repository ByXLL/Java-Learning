package com.brodog.mq.config;


import com.brodog.mq.receiver.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
;

/**
 * rabbit配置类
 * @author By-Lin
 */
@Configuration
@SuppressWarnings("all")
public class RabbitConfig {
    @Value("${spring.rabbitmq.addresses}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Autowired
    private Receiver receiver;


    /**
     * 连接工厂工厂模式
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses + ":" + port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        // 消息发送确认的开关 打开
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 获取 rabbitAdmin 类
     * 对RabbitM Q的管理操作 exchange queue bingding 进行配置@Bean
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * 创建一个 Template生产者
     * @return
     */
    @Bean
    public RabbitTemplate newRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        // 设置 消息投递到 MQ-Server 给发送方的回调
        rabbitTemplate.setConfirmCallback(confirmCallback());
        // 设置 发送消息通知开关
        rabbitTemplate.setMandatory(true);
        // 设置路由失败 回调参数 (只有失败的时候会去触发)
        rabbitTemplate.setReturnCallback(returnCallback());
        return rabbitTemplate;
    }


    /**
     * 声明交换器 Direct交换器
     * @return
     */
    @Bean
    public DirectExchange directexchange() {
        return new DirectExchange("DirectExchange");
    }

    /**
     * topic交换器正则表达式的匹配
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("TopicExchange");
    }

    /**
     * Fanout交换器
     * @return
     */
    @Bean
    public FanoutExchange fanoutexchange() {
        return new FanoutExchange("FanoutExchange");

    }

    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue queue1() { return new Queue("queue1");}

    @Bean
    public Queue queue2() { return new Queue( "queue2");}


    /**
     * 绑定关系queuel 与direct绑定 直连 并且routingkey为dog
     * @return
     */
    @Bean
    public Binding bindingQueue1Direct() {
        return BindingBuilder
                .bind(queue1())
                .to(directexchange())
                .with("dog");
    }


    /**
     * 绑定关系queue2与direct绑定
     * @return
     */
    @Bean
    public Binding bindingQueue2Direct() {
        return BindingBuilder
                .bind(queue2())
                .to(directexchange())
                .with("bro");
    }


    /**
     * 生产者发送消息到达MQ-Server 或者说到达交换机回调监听
     * 但是如果队列或者routingey不存在则消息还是会丢失
     */
    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback() {
        return new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                // ack 返回
                if(b) {
                    System.out.println("发送者确认发送消息到MQ-Server成功");
                }else {
                    System.out.println("!!!!!发送者消息发送到MQ-Server失败，请重试");
                }
            }
        };
    }

    /**
     * 生产者发送消息失败  队列或者routingey不存在  回调监听
     * 只会在失败的时候回调
     */
    @Bean
    public RabbitTemplate.ReturnCallback returnCallback() {
        return new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("==============================================\n");
                System.out.println("       !!!!!!!!!  MQ发送失败   !!!!!!!!!  \n");
                System.out.println("         ReplyCode: "+ i +" \n");
                System.out.println("         ReplyText: "+ s +" \n");
                System.out.println("         Exchange: "+ s1 +" \n");
                System.out.println("         routingKey: "+ s2 +" \n");
                System.out.println("         Message.Body: "+ message.getBody().toString() +" \n");
                System.out.println("==============================================");
            }
        };
    }

    /**
     * 消费者 手动确认的配置连接类
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory());
        // 设置绑定的队列
        listenerContainer.setQueues(queue1());

        // 设置手动提交
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        // 设置自定义消费监听 用于实现手动返回ack
        listenerContainer.setMessageListener(receiver);

        return listenerContainer;
    }
}
