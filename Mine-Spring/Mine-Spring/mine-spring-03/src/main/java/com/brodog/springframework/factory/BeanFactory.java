package com.brodog.springframework.factory;

import com.brodog.springframework.BeansException;

/**
 * Bean 工厂根接口
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public interface BeanFactory {
    /**
     * 通过beanName获取 bean的实例对象 Object
     * @param beanName  beanName
     * @return  bean的实例对象 Object
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 返回含构造函数的 Bean 实例对象 Object
     * @param beanName 要检索的bean的名称
     * @param args 构造函数入参
     * @return 实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    Object getBean(String beanName, Object... args) throws BeansException;
}
