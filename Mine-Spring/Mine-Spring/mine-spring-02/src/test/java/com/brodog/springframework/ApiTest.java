package com.brodog.springframework;

import com.brodog.springframework.bean.UserService;
import com.brodog.springframework.factory.config.BeanDefinition;
import com.brodog.springframework.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public class ApiTest {

    @Test
    public void testSpringFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        beanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.addUser();

        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.addUser();
    }
}
