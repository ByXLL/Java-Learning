package com.brodog.springframework.factory.support;

import com.brodog.springframework.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean 工厂的核心实现类
 * 通过当前实现类 具备完整的获取和创建一个bean的能力
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public class DefaultListableBeanFactory extends AbstractAutoWireCapableBeanFactory implements BeanDefinitionRegistry{
    /**
     * bean 实现信息池
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 通过beanName获取 beanDefinition
     * 抽象方法 等待下面的子类进行实现 提供给 当前类内部的 getBean()
     *
     * @param beanName beanName
     * @return
     */
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    /**
     * 往注册表中注册 bean信息
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     */
    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
