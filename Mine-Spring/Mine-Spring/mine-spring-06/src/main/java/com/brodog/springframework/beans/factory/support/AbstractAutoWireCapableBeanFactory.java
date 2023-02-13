package com.brodog.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.PropertyValue;
import com.brodog.springframework.beans.PropertyValues;
import com.brodog.springframework.beans.factory.config.BeanDefinition;
import com.brodog.springframework.beans.factory.config.BeanReference;
import com.brodog.springframework.beans.factory.config.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * 抽象的 具有一定自动能力的 bean工厂
 * 继承自 AbstractBeanFactory 重写 createBeanObject 方法
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建 beanObject 实例对象
     * 抽象方法 等待下面的子类进行实现 提供给 当前类内部的 getBean()
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     * @param args  构造函数参数
     * @return
     */
    @Override
    protected Object createBeanObject(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object beanObject = null;
        // 创建实例对象的Object
        try {
            beanObject = createBeanObjectInstance(beanName, beanDefinition, args);
        }catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 属性填充
        applyPropertyValues(beanName, beanDefinition, beanObject);
        // 将实例注册到单例池
        registerSingletonBean(beanName, beanObject);
        return beanObject;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 创建beanObject的实例
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     * @param args  可变参数集合
     * @return  beanObject实例
     */
    private Object createBeanObjectInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructor = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        if(Objects.isNull(args) || args.length == 0) {
            return getInstantiationStrategy().instantiate(beanName, beanDefinition, null, null);
        }
        for (Constructor declaredConstructor : declaredConstructors) {
            // 这里简单通过判断当前构造方法的参数个数与传进来的参数匹配 则代表使用当前这个构造方法进行创建 （真实方案还得通过判断每个参数的类型）
            if(declaredConstructor.getParameterTypes().length == args.length) {
                constructor = declaredConstructor;
                break;
            }
        }

        return getInstantiationStrategy().instantiate(beanName, beanDefinition, constructor, args);
    }

    /**
     * 属性绑定
     * @param beanName          beanName
     * @param beanDefinition    beanDefinition
     * @param beanObject        beanObject
     */
    private void applyPropertyValues(String beanName, BeanDefinition beanDefinition, Object beanObject) {
        try {
            PropertyValues propertyValues =  beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String propertyValueName = propertyValue.getName();
                Object propertyValueValue = propertyValue.getValue();

                // 如果当前这个属性也是一个引用bean对象  A引用了B
                if(propertyValueValue instanceof BeanReference) {
                    // 拿到当前引用的这个类的引用信息
                    BeanReference beanReference = (BeanReference) propertyValueValue;
                    // 通过beanName获取到实例
                    propertyValueValue = getBean(beanReference.getBeanName());
                }

                // 属性填充
                BeanUtil.setFieldValue(beanObject, propertyValueName, propertyValueValue);
            }
        }catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }
}
