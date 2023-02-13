package com.brodog.springframework.beans.factory;

import com.brodog.springframework.beans.BeansException;

import java.util.Map;

/**
 * 一个扩展 BeanFactory 工厂接口的接口
 * @author By-BroDog
 * @createTime 2023-02-08
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 按照类型返回 Bean 实例
     * @param type  类型
     * @param <T>   Class
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
