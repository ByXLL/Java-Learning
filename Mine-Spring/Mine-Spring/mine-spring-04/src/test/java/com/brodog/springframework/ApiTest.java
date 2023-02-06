package com.brodog.springframework;


import com.brodog.springframework.bean.GoodsService;
import com.brodog.springframework.bean.UserDao;
import com.brodog.springframework.bean.UserService;
import com.brodog.springframework.factory.PropertyValue;
import com.brodog.springframework.factory.PropertyValues;
import com.brodog.springframework.factory.config.BeanDefinition;
import com.brodog.springframework.factory.config.BeanReference;
import com.brodog.springframework.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author By-BroDog
 * @createTime 2023-02-04
 */
public class ApiTest {

    /**
     * 测试使用bean工厂进行bean的创建
     */
    @Test
    public void testBeanFactory() {
        // 创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());

        // 因为没有做循环依赖的缓存 所以提前注册该属性
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 创建UserService bean的属性
        PropertyValues propertyValues = new PropertyValues();
        // 定义属性 注意这里要使用 BeanReference 做声明，代表该属性也是一个bean的引用信息
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 定义bean信息
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);

        // 注册bean信息
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "张三");
        userService.insertUser();

        // 调用别的bean的方法
        userService.findUserName("10002");
    }

    /**
     * 测试无参bean的创建
     */
    @Test
    public void testBeanFactory2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(GoodsService.class);
        beanFactory.registryBeanDefinition("goodsService", beanDefinition);

        GoodsService goodsService = (GoodsService) beanFactory.getBean("goodsService");
        goodsService.addGoods();
    }
}
