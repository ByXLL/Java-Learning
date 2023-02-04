package com.brodog.springframework.factory.support;

import com.brodog.springframework.factory.config.BeanDefinition;

/**
 * 抽象的 具有一定自动能力的 bean工厂
 * 继承自 AbstractBeanFactory 重写 createBeanObject 方法
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 创建beanObject
     * AbstractBeanFactory 重写 createBeanObject方法 用于在这一层 单独把bean创建的能力进行实现
     * @param beanName
     * @param beanDefinition
     * @return
     */
    @Override
    protected Object createBeanObject(String beanName, BeanDefinition beanDefinition) {
        Object beaInstance = null;
        try {
            // 通过获取 Class 类对象 通过反射的方式创建其实例
            beaInstance = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // bean对象实例创建完成后 将当前对象注册至单例池中
        registerSingletonBean(beanName, beaInstance);
        return beaInstance;
    }
}
