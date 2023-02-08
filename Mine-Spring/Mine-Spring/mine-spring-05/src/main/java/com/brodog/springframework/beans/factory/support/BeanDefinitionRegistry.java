package com.brodog.springframework.beans.factory.support;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.config.BeanDefinition;

/**
 * bean信息 注册接口
 * @author By-BroDog
 * @createTime 2023-02-04
 */
public interface BeanDefinitionRegistry {
    /**
     * 往注册表中注册 bean信息
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用Bean名称查询BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
