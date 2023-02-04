package com.brodog.springframework.factory.config;

import java.lang.reflect.Constructor;

/**
 * 定义实例化策略接口
 * @author By-BroDog
 * @createTime 2023-02-04
 */
public interface InstantiationStrategy {

    /**
     * 初始化实例
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     * @param constructor   constructor
     * @param args  参数集合
     * @return
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args);
}
