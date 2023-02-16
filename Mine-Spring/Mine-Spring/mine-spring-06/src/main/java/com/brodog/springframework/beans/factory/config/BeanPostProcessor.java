package com.brodog.springframework.beans.factory.config;

import com.brodog.springframework.beans.BeansException;

/**
 * 声明用于修改新实例化 Bean 对象的扩展点具备的能力
 * BeanPostProcessor是在spring容器加载了bean的定义文件并且实例化bean之后执行的
 * 在 Bean 对象实例化之后,提供了修改新实例化 Bean 对象的扩展点，也可以替换 Bean 对象。与 AOP 有着密切的关系
 * @author By-BroDog
 * @createTime 2023-02-14
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param beanName  beanName
     * @param bean      bean
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(String beanName, Object bean) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     * @param beanName
     * @param bean
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(String beanName, Object bean) throws BeansException;
}
