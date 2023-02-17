package com.brodog.springframework.context.support;

import com.brodog.springframework.beans.BeansException;

/**
 * 应用上下文实现类
 * 类路径下解析xml文件获取的应用上下文类
 * 主要是对继承抽象类中方法的调用和提供了配置文件地址信息
 * @author By-BroDog
 * @createTime 2023-02-15
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        // 刷新上下文
        refresh();
    }

    /**
     * 抽象方法 获取配置信息的详细描述
     * 此方法是为了从入口上下文类，拿到配置信息的地址描述
     * 通知当前类的构造方法将 location进行传入，使得上下文可以获取到
     * @return
     */
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
