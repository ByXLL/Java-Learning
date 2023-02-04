package com.brodog.springframework.factory.support;

import com.brodog.springframework.BeansException;
import com.brodog.springframework.factory.config.BeanDefinition;
import com.brodog.springframework.factory.config.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * 抽象的 具有一定自动能力的 bean工厂
 * 继承自 AbstractBeanFactory 重写 createBeanObject 方法
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建beanObject
     * AbstractBeanFactory 重写 createBeanObject方法 用于在这一层 单独把bean创建的能力进行实现
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     * @return
     */
    @Override
    protected Object createBeanObject(String beanName, BeanDefinition beanDefinition) {
        // 通过 定义好的创建bean策略进程beanObject的创建
        return createBeanObjectInstance(beanName, beanDefinition, null);
    }

    /**
     * 创建 beanDefinition
     * 抽象方法 等待下面的子类进行实现 提供给 当前类内部的 getBean()
     * @param beanName  beanName
     * @param beanDefinition    beanDefinition
     * @param args  构造函数参数
     * @return
     */
    @Override
    protected Object createBeanObject(String beanName, BeanDefinition beanDefinition, Object[] args) {
        // 通过 定义好的创建bean策略进程beanObject的创建
        return createBeanObjectInstance(beanName, beanDefinition, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
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
        // 拿到当前Class的所有的构造参数
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            // 这里简单通过判断当前构造方法的参数个数与传进来的参数匹配 则代表使用当前这个构造方法进行创建 （真实方案还得通过判断每个参数的类型）
            if(Objects.nonNull(args) && declaredConstructor.getParameterTypes().length == args.length) {
                constructor = declaredConstructor;
                break;
            }
        }
        // 通过具体实例的策略 进行实例化
        return getInstantiationStrategy().instantiate(beanName, beanDefinition, constructor, args);
    }
}
