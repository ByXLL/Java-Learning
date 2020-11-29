package com.example.springboot.compotent;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 登录拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 目标方法执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取之前登录成功设置的session
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null) {
            // 设置提示信息
            request.setAttribute("msg","没有权限请先登录");
            // 通过转发器 返回登录页面  并将请求和响应 转发出去
            request.getRequestDispatcher("/login").forward(request,response);
            // 未登录 不放行请求
            return false;
        }else {
            // 登录了 放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
