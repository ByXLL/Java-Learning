package com.brodog.springframework.context;

import com.brodog.springframework.beans.factory.ListableBeanFactory;

/**
 * 应用上下文接口
 * 继承于 ListableBeanFactory，主要是为了继承了关于 BeanFactory 接口，比如一些 getBean 的方法
 * @author By-BroDog
 * @createTime 2023-02-14
 */
public interface ApplicationContext extends ListableBeanFactory {

}
