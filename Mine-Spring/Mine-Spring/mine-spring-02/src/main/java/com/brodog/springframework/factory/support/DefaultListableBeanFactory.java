package com.brodog.springframework.factory.support;

import com.brodog.springframework.BeansException;
import com.brodog.springframework.factory.config.BeanDefinition;

import java.util.Map;
import java.util.Objects;
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
     * @param beanName beanName
     * @return
     */
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(Objects.isNull(beanDefinition)) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    /**
     * 往注册表中注册 bean信息
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
