package com.brodog.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 工厂
 * 通过简单的声明一个存放bean信息的内部容器与注册和获取方法，简单实现bean容器
 * @author By-BroDog
 * @createTime 2023-02-01
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 通过beanName 获取beanDefinition
     * @param beanName  beanName
     * @return
     */
    public Object getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    /**
     * 注册bean信息
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     */
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
