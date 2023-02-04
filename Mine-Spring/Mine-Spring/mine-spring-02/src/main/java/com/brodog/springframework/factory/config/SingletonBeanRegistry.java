package com.brodog.springframework.factory.config;

/**
 * 单例bean 注册表接口
 * 声明 注册表相关能力 具备 通过beanName获取 单例对象 以及 往单例池中注册新的bean信息
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public interface SingletonBeanRegistry {

    /**
     * 返回在给定名称下注册的（原始）单例对象。
     * @param beanName  beanName
     * @return
     */
    Object getSingletonBeanObject(String beanName);

    /**
     * 往单例池中注册单例对象
     * @param beanName  beanName
     * @param singleBeanObject  单例bean对象
     */
    void registerSingletonBean(String beanName, Object singleBeanObject);
}
