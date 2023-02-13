package com.brodog.springframework.beans.factory.support;

import com.brodog.springframework.beans.factory.config.BeanDefinition;
import com.brodog.springframework.beans.factory.config.InstantiationStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * CGLib动态代理创建实例的策略实现类
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * 初始化实例
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @param constructor    constructor
     * @param args           参数集合
     * @return
     */
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(Objects.isNull(constructor)) {
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
