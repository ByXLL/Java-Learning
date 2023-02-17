package com.brodog.springframework.test06.common;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.config.BeanPostProcessor;
import com.brodog.springframework.test06.beans.UserService;

/**
 * 在
 * @author By-BroDog
 * @createTime 2023-02-16
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanName beanName
     * @param bean     bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) throws BeansException {
        if("userService".equals(beanName)) {
            UserService userService  = (UserService) bean;
            System.out.println("--------------------  "+ beanName +" 在对象执行初始化之前进行增强 ------------------------");
            userService.setCompany("深圳-" + userService.getCompany());
        }
        return bean;
    }

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param beanName
     * @param bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) throws BeansException {
        if("userService".equals(beanName)) {
            UserService userService  = (UserService) bean;
            System.out.println("--------------------  "+ beanName +" 在对象执行初始化之后进行增强 ------------------------");
            userService.setCompany(userService.getCompany() + "-南山区");
        }
        return bean;
    }
}
