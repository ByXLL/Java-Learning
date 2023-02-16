package com.brodog.springframework.beans.factory.support;

import com.brodog.springframework.beans.BeansException;
import com.brodog.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.brodog.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean 工厂的核心实现类
 * 通过当前实现类 具备完整的获取和创建一个bean的能力
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public class DefaultListableBeanFactory extends AbstractAutoWireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    /**
     * bean 实现信息池
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();


    /**
     * 往注册表中注册 bean信息
     * 来自 BeanDefinitionRegistry 接口
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     */
    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 使用Bean名称查询BeanDefinition
     * 来自 BeanDefinitionRegistry 接口
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(Objects.isNull(beanDefinition)) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    /**
     * 提前实例化单例对象
     *
     * @throws BeansException
     */
    @Override
    public void preInstantiateSingletons() throws BeansException {
        // getBean(beanName)
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    /**
     * 判断是否包含指定名称的BeanDefinition
     * 来自 BeanDefinitionRegistry 接口
     * @param beanName
     * @return
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 返回注册表中所有的Bean名称
     * 来自 BeanDefinitionRegistry 接口
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    /**
     * 按照类型返回 Bean 实例
     * 来自 ConfigurableListableBeanFactory 接口
     * @param type 类型
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }
}
