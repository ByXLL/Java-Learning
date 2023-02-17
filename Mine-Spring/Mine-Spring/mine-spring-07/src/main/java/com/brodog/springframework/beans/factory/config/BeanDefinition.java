package com.brodog.springframework.beans.factory.config;

import com.brodog.springframework.beans.PropertyValues;

import java.util.Objects;

/**
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class BeanDefinition {
    private Class beanClass;

    /**
     * 当前bean的属性集合
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = Objects.nonNull(propertyValues) ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

}
