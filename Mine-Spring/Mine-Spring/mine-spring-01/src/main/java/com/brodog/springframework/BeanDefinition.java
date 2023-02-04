package com.brodog.springframework;

/**
 * 用于定义 Bean 实例化信息
 * @author By-BroDog
 * @createTime 2023-02-01
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
