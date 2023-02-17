package com.brodog.springframework.context.support;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.brodog.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 实现了刷新上下文能力的 抽象类
 * 继承自AbstractApplicationContext 实现其中的 refreshBeanFactory()、getBeanFactory() 抽象方法
 * 并定义一个抽象方法 加载BeanDefinitions 让子类进行具体实现
 * @author By-BroDog
 * @createTime 2023-02-14
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    /**
     * 用于生产的 beanFactory
     */
    private DefaultListableBeanFactory beanFactory;

    /**
     * 刷新 BeanFactory
     * @throws BeansException
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        System.out.println("-------------------- beanFactory创建完成 ------------------------");
        loadBeanDefinitions(beanFactory);
        System.out.println("-------------------- 加载beanDefinition完成 ------------------------");
        this.beanFactory = beanFactory;
    }

    /**
     * 获取BeanFactory
     * @return
     */
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 加载 BeanDefinition 定义信息
     * 让子类实现解析 xml、文件、url获取beanDefinition的事情
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    /**
     * 创建默认的可用于生产 BeanFactory
     * @return
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
}
