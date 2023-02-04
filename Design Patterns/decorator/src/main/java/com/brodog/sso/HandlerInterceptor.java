package com.brodog.sso;

/**
 * 第三包中的模拟请求拦截器
 */
public interface HandlerInterceptor {
    boolean preHandle(String request, String response, Object handler);
}
