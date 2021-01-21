package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * redis 配置类
 */
@Configuration
public class RedisConfig {
    /**
     * redis 模板配置
     * 需要将什么对象装配到容器中 就返回什么类型的数据
     * 在spring中 使用某一个 Factory 通过参数的形式注入 这个时候 spring 会默认将该 bean 注入进来
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        // 实例化 bean
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 设置 key 的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        // 设置 value 的序列化方式
        template.setValueSerializer(RedisSerializer.json());
        // 设置 hash的key 和 value的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.json());

        // 触发配置
        template.afterPropertiesSet();
        return template;
    }
}
