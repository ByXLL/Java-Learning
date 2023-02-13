package com.brodog.springframework.beans.factory.support;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.core.io.Resource;
import com.brodog.springframework.core.io.ResourceLoader;


/**
 * Bean定义读取接口
 * getBeanDefinitionRegistry()、getResourceLoader() 都是用于提供给后面三个方法的工具，加载和注册，这两个方法的实现会包装到抽象类中，以免污染具体的接口实现方法。
 * @author By-BroDog
 * @createTime 2023-02-08
 */
public interface BeanDefinitionReader {

    /**
     * 获取bean信息 注册接口
     * @return
     */
    BeanDefinitionRegistry getBeanDefinitionRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 触发加载 BeanDefinitions
     * @param resource      资源加载的实现方式
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 触发加载 BeanDefinitions
     * @param resources      资源加载的实现方式
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 触发加载 BeanDefinitions
     * @param location      文件路径
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;
}
