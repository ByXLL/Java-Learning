package com.example.redisdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author By-Lin
 */
@Configuration
public class RedisConfig {

//    /**
//     * redis的连接工厂
//     * @return  连接工厂
//     */
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
////        return new LettuceConnectionFactory();
//        return new JedisConnectionFactory();
//    }

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String,Serializable> redisTemplate = new RedisTemplate<>();

        // 指定连接工厂
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        // 设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化方式json
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置hash的key的序列化方式为String
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置hash的value的序列化方式json
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
//
//    @Bean
//    public RedisTemplate<String, Object> stringSerializerRedisTemplate() {
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(stringSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(stringSerializer);
//        return redisTemplate;
//    }

}
