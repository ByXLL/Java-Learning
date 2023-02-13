package com.brodog.springframework.beans.factory.support;

import com.brodog.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认单例bean注册接口实现类
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class DefaultSingleBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonBeanObjectMap = new ConcurrentHashMap<>();

    /**
     * 返回在给定名称下注册的 原始的单例对象
     *
     * @param beanName beanName
     * @return
     */
    @Override
    public Object getSingletonBeanObject(String beanName) {
        return singletonBeanObjectMap.get(beanName);
    }

    /**
     * 往单例池中注册 单例bean对象
     *
     * @param beanName            beanName
     * @param singletonBeanObject 单例beanObject
     */
    @Override
    public void registerSingletonBean(String beanName, Object singletonBeanObject) {
        singletonBeanObjectMap.put(beanName, singletonBeanObject);
    }
}
