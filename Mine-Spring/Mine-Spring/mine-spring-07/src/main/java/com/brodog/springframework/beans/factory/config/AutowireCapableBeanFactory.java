package com.brodog.springframework.beans.factory.config;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.BeanFactory;

/**
 * 自动的具备能力的Bean工厂
 * 声明为接口，主要声明两个方法，用于触发执行 BeanPostProcessors 接口实现类的
 * postProcessBeforeInitialization前置操作和后置操作 对已经存在的beanObject进行增强操作
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 触发执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 前置增强方法
     * @param beanName      beanName
     * @param existingBean  已经创建出来的beanObject
     * @return  经过前置增强的beanObject
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object existingBean) throws BeansException;

    /**
     * 触发执行 BeanPostProcessors 接口实现类的 processorsAfterInitialization 后置增强方法
     * @param beanName      beanName
     * @param existingBean  已经创建出来的beanObject
     * @return  经过后置置增强的beanObject
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(String beanName, Object existingBean) throws BeansException;
}
