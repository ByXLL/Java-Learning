package com.brodog.springframework.test;

import com.brodog.springframework.BeanDefinition;
import com.brodog.springframework.BeanFactory;
import com.brodog.springframework.service.UserService;
import org.junit.Test;

/**
 * @author By-BroDog
 * @createTime 2023-02-01
 */
public class ApiTest {

    @Test
    public void testApi() {
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        beanFactory.registryBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBeanDefinition("userService");
        userService.addUser();
    }
}
