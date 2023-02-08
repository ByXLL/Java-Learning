package com.brodog.springframework.beans.factory.config;

/**
 * Bean 引用信息
 * @author By-BroDog
 * @createTime 2023-02-06
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
