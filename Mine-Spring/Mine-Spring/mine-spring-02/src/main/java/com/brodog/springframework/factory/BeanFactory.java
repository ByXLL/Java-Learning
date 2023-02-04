package com.brodog.springframework.factory;

import com.brodog.springframework.BeansException;

/**
 * Bean 工厂根接口
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public interface BeanFactory {
    /**
     * 通过beanName获取 bean的实例对象 Object
     * @param beanName  beanName
     * @return  bean的实例对象 Object
     */
    Object getBean(String beanName) throws BeansException;
}
