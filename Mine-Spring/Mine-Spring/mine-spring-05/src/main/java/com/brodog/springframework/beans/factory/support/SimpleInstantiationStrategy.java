package com.brodog.springframework.beans.factory.support;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.config.BeanDefinition;
import com.brodog.springframework.beans.factory.config.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 简单的对于创建 使用jdk的反射 对象实例的策略实现类
 * @author By-BroDog
 * @createTime 2023-02-04
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 初始化实例
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @param constructor    constructor
     * @param args           参数集合
     * @return
     */
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            // 如果存在带参构造方法
            if(Objects.nonNull(constructor)) {
                return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
            // 无参直接反射创建实例
            return beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
