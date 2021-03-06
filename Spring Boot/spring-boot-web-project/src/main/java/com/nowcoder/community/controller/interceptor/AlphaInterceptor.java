package com.nowcoder.community.controller.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器 模板  实现  接口
 */
@Component
public class AlphaInterceptor implements HandlerInterceptor {
//    private static final Logger logger = LoggerFactory.getLogger(AlphaInterceptor.class);

    /**
     * 在调用  Controller 之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.debug("preHandle: " + handler.toString());
        System.out.println("preHandle: " + handler.toString());
        return true;
    }

    /**
     * 在调用完 Controller 之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle: " + handler.toString());
    }

    /**
     * 在调用完 TemplateEngine 之后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion: " + handler.toString());
    }
}
