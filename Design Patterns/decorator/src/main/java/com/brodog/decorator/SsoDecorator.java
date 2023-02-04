package com.brodog.decorator;

import com.brodog.sso.HandlerInterceptor;
import com.brodog.sso.SsoInterceptor;

/**
 * 单点登录 装饰器
 * 抽象类 实现第三方包中的接口 简单具备相同的功能
 * @author By-BroDog
 * @createTime 2023-01-22
 */
public abstract class SsoDecorator implements HandlerInterceptor{

    /**
     * 关键步骤，添加一个内部属性为三方包中的类的类型，并通过构造函数中对当前属性进行初始化
     */
    private HandlerInterceptor handlerInterceptor;

    private SsoDecorator() {}

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    /**
     * 简单实现接口的方法，但是只是封装了一层，只做一个简单的调用，具体添加的装饰需要在当前类的具体实现类中进行处理
     * 核心东西就是调用继承接口中的方法，这样保证了不对别人三方库的代码修改，如需修改只用更新三方库即可，我们无须感知
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean preHandle(String request, String response, Object handler) {
        return handlerInterceptor.preHandle(request, response, handler);
    }
}
