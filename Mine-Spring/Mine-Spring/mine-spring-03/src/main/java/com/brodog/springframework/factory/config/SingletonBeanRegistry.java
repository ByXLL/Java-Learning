package com.brodog.springframework.factory.config;

/**
 * 单例bean注册器
 * 声明 注册表相关能力 具备 通过beanName获取 单例对象 以及 往单例池中注册新的bean信息
 * @author By-BroDog
 * @createTime 2023-02-04
 */
public interface SingletonBeanRegistry {
    /**
     * 返回在给定名称下注册的 原始的单例对象
     * @param beanName  beanName
     * @return
     */
    Object getSingletonBeanObject(String beanName);

    /**
     * 往单例池中注册 单例bean对象
     * @param beanName  beanName
     * @param singleBeanObject  单例beanObject
     */
    void registerSingleBean(String beanName, Object singleBeanObject);
}
