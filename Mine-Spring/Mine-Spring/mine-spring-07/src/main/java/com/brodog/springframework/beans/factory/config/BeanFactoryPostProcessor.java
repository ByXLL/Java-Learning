package com.brodog.springframework.beans.factory.config;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 提供自定义修改 BeanDefinition 属性信息能力的接口
 * 允许在 Bean 对象注册后但未实例化之前，对 Bean 的定义信息 BeanDefinition 执行修改操作
 * @author By-BroDog
 * @createTime 2023-02-14
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     * @param beanFactory           beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
