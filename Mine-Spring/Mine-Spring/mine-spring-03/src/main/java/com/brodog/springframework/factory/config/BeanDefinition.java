package com.brodog.springframework.factory.config;

/**
 * Bean实例信息
 * @author By-BroDog
 * @create 2023-02-02
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
