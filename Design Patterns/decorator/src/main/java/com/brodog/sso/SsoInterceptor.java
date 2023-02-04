package com.brodog.sso;


/**
 * 装饰器 单点登录 对请求拦截接口进行一个继承，这样保证了具备有原来的功能
 * 定义当前类为抽象类 继承自公共框架的接口
 * @author By-BroDog
 * @createTime 2023-01-22
 */
public abstract class SsoInterceptor implements HandlerInterceptor {

    public boolean preHandle(String request, String response, Object handler) {
        // todo 简单模拟sso鉴权
        return false;
    }
}