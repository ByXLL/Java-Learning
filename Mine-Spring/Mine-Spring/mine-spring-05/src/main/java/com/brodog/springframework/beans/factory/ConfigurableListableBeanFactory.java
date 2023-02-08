package com.brodog.springframework.beans.factory;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.brodog.springframework.beans.factory.config.BeanDefinition;
import com.brodog.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean以及预先实例化的操作接口
 * @author By-BroDog
 * @createTime 2023-02-08
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 通过名字获取 BeanDefinition
     * @param beanName  beanName
     * @return
     * @throws BeansException
     */
   BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
