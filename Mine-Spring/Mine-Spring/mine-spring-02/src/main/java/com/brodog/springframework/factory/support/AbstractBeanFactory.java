package com.brodog.springframework.factory.support;

import com.brodog.springframework.BeansException;
import com.brodog.springframework.factory.BeanFactory;
import com.brodog.springframework.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * 抽象bean 工厂
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public abstract class AbstractBeanFactory extends DefaultSingleBeanRegistry implements BeanFactory {

    /**
     * 通过beanName获取 bean的实例对象 Object
     * 重写 beanFactory接口方法
     * @param beanName  beanName
     * @return  bean的实例对象 Object
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        // 通过 beanName 获取单例bean的Object对象 调用的是DefaultSingleBeanRegistry中定义的 getSingletonBeanObject
        Object singletonBeanObject = getSingletonBeanObject(beanName);
        // 如果不存在则创建 该bean的Object对象
        if(Objects.isNull(singletonBeanObject)) {
            // 先获取到bean定义信息
            BeanDefinition beanDefinition = getBeanDefinition(beanName);
            // 再创建单例beanObject
            return createBeanObject(beanName, beanDefinition);
        }
        return singletonBeanObject;
    }

    /**
     * 通过beanName获取 beanDefinition
     * 抽象方法 使用等待下面的子类进行实现 提供给 当前类内部的 getBean()
     * @param beanName  beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 创建 beanDefinition
     * 抽象方法 等待下面的子类进行实现 提供给 当前类内部的 getBean()
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected abstract Object createBeanObject(String beanName, BeanDefinition beanDefinition);
}
