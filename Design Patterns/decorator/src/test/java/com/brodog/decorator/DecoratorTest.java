package com.brodog.decorator;

import com.brodog.sso.SsoInterceptor;
import org.junit.Test;

/**
 * 装饰器模式
 * 在不改变现有对象结果的情况下，动态地给该对象添加一些职责（附加其额外的功能）的模式
 * 一般用于导入了一个第三方的包，不能改变其内部大逻辑代码，但是需要对其功能进行自定义变更或者说添加功能的情况下
 * @author By-BroDog
 * @createTime 2023-01-22
 */
public class DecoratorTest {

    @Test
    public void testOssLoginDecorator() {
        SsoLoginDecorator ssoLoginDecorator = new SsoLoginDecorator(new SsoInterceptor() {});
        boolean preHandle = ssoLoginDecorator.preHandle("zhangsan", "ok", "t");
        System.out.println(preHandle);
    }
}
