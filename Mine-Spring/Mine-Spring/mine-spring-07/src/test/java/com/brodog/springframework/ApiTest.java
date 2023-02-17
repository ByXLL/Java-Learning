package com.brodog.springframework;

import com.brodog.springframework.beans.*;
import com.brodog.springframework.beans.factory.config.BeanDefinition;
import com.brodog.springframework.beans.factory.config.BeanReference;
import com.brodog.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.brodog.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.brodog.springframework.context.support.ClassPathXmlApplicationContext;
import com.brodog.springframework.test06.beans.GoodsService;
import com.brodog.springframework.test06.beans.UserDao;
import com.brodog.springframework.test06.beans.UserService;
import com.brodog.springframework.test06.common.MyBeanFactoryPostProcessor;
import com.brodog.springframework.test06.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class ApiTest {

    @Test
    public void testBeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class, "张三");
        userService.insertUser();
        userService.findUserName("10001");
        System.out.println(userService);
    }

    @Test
    public void testContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.insertUser();
    }

    @Test
    public void testXML() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class, "张三");
        userService.insertUser();
    }

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
        propertyValues.addPropertyValue(new PropertyValue("userId", "10001"));
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
