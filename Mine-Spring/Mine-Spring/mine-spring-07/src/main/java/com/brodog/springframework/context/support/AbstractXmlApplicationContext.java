package com.brodog.springframework.context.support;

import com.brodog.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.brodog.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Objects;

/**
 * 抽象类解析xml文件 对配置信息的加载
 * 抽象类 继承至 AbstractRefreshableApplicationContext 主要实现xml来源的bean信息加载
 * @author By-BroDog
 * @createTime 2023-02-14
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 加载 BeanDefinition 定义信息
     * 让子类实现解析 xml、文件、url获取beanDefinition的事情
     * @param beanFactory
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // 使用 XmlBeanDefinitionReader 类，处理关于 XML 文件配置信息的操作
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(Objects.nonNull(configLocations)) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 抽象方法 获取配置信息的详细描述
     * 此方法是为了从入口上下文类，拿到配置信息的地址描述
     * @return
     */
    protected abstract String[] getConfigLocations();
}
