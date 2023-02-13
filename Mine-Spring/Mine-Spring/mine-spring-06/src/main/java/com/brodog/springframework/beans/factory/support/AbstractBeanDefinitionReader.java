package com.brodog.springframework.beans.factory.support;

import com.brodog.springframework.core.io.DefaultResourceLoader;
import com.brodog.springframework.core.io.ResourceLoader;

/**
 * Bean定义读取接口抽象类实现
 * 这此抽象类中对接口 通过构造方法 完成了BeanDefinitionReader 获取beanDefinition注册的能力和获取资源加载器的接口能力的具体实现
 * 声明为抽象类，让下游子类继续持有这些能力
 * 这样在接口 BeanDefinitionReader 的具体实现类中，就可以把解析后的 XML 文件中的 Bean 信息，注册到 Spring 容器去了。
 * 以前我们是通过单元测试使用，调用 BeanDefinitionRegistry 完成Bean的注册，现在可以放到 XMl 中操作了
 * @author By-BroDog
 * @createTime 2023-02-08
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    /**
     * bean信息 注册接口
     * 让子类或者实现类具有 注册注册BeanDefinition的能力
     */
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this(beanDefinitionRegistry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
