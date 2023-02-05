package com.brodog.springframework;

import com.brodog.springframework.bean.GoodsService;
import com.brodog.springframework.bean.UserService;
import com.brodog.springframework.factory.config.BeanDefinition;
import com.brodog.springframework.factory.support.DefaultListableBeanFactory;
import com.brodog.springframework.factory.support.SimpleInstantiationStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

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
        // 定义bean信息
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        // 注册bean信息
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "张三");
        userService.insertUser();
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

    /**
     * 测试使用 CGLib创建bean
     */
    @Test
    public void testCGLib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        UserService userService = (UserService) enhancer.create(new Class[]{String.class}, new Object[]{"李四"});
        userService.insertUser();
    }

    /**
     * 测试创建bean 直接通过实例化的形势
     */
    @Test
    public void testNewInstance() {
        try {
            UserService userService = UserService.class.newInstance();
            userService.insertUser();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过反射实例化
     */
    @Test
    public void testConstructor() {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        Constructor<?> declaredConstructor = declaredConstructors[0];

        try {
            Constructor<UserService> userServiceConstructor = userServiceClass.getDeclaredConstructor(declaredConstructor.getParameterTypes());
            UserService userService = userServiceConstructor.newInstance("李四");
            userService.insertUser();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
