package com.brodog.springframework.factory.support;

import com.brodog.springframework.factory.config.BeanDefinition;

/**
 * bean信息 注册接口
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public interface BeanDefinitionRegistry {

    /**
     * 往注册表中注册 bean信息
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
