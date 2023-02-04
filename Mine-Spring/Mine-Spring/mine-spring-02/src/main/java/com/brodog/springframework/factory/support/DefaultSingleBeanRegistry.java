package com.brodog.springframework.factory.support;

import com.brodog.springframework.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例bean注册能力的默认实现
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public class DefaultSingleBeanRegistry implements SingletonBeanRegistry {

    /**
     * 单例beanObject池
     */
    private Map<String, Object> singletonBeanObjectMap = new ConcurrentHashMap<>();

    /**
     * 返回在给定名称下注册的（原始）单例对象。
     *
     * @param beanName beanName
     * @return
     */
    @Override
    public Object getSingletonBeanObject(String beanName) {
        return singletonBeanObjectMap.get(beanName);
    }

    /**
     * 往单例池中注册单例对象
     *
     * @param beanName         beanName
     * @param singleBeanObject 单例bean对象
     */
    @Override
    public void registerSingletonBean(String beanName, Object singleBeanObject) {
        singletonBeanObjectMap.put(beanName, singleBeanObject);
    }
}
