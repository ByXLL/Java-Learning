package com.brodog.springframework.context.support;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.brodog.springframework.beans.factory.ListableBeanFactory;
import com.brodog.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.brodog.springframework.beans.factory.config.BeanPostProcessor;
import com.brodog.springframework.context.ConfigurableApplicationContext;
import com.brodog.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 应用上下文抽象类实现
 * 继承 DefaultResourceLoader 是为了处理 spring.xml 配置资源的加载
 * 在此抽象类中去实现 ConfigurableApplicationContext接口中的refresh能力，并重写 ListableBeanFactory 和BeanFactory 中的方法
 * 并声明两个抽象方法，由后面的子类进行实现
 * @author By-BroDog
 * @createTime 2023-02-14
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 刷新上下文
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // 创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        // 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在Bean实例化之前，执行 BeanFactoryPostProcess
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 刷新 BeanFactory
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取BeanFactory
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType, args);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    /**
     * 调用 所有在BeanDefinition 加载完成后，实例化 Bean 对象之前，需要对BeanDefinition 属性进行操作的操作
     * @param beanFactory   beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        System.out.println("-------------------- 加载完成即将对beanDefinition进行增强 ------------------------");
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册 需要进行前后置处理的处理器
     * @param beanFactory   beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }
}
