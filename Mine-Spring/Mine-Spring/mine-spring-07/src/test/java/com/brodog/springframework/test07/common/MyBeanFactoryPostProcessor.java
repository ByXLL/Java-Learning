package com.brodog.springframework.test06.common;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.PropertyValue;
import com.brodog.springframework.beans.PropertyValues;
import com.brodog.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.brodog.springframework.beans.factory.config.BeanDefinition;
import com.brodog.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * 自定义的 在加载完成BeanDefinition之后，实例化之前，对BeanDefinition进行扩展操作
 * @author By-BroDog
 * @createTime 2023-02-16
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        System.out.println("-------------------- beanDefinition加载完成了,在实例化之前进行增强 ------------------------");
        propertyValues.addPropertyValue(new PropertyValue("company", "Tencent"));
    }
}
