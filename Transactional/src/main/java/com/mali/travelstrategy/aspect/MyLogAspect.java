package com.mali.travelstrategy.aspect;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.mali.travelstrategy.annotation.MyLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义日志注解切面
 * @author By-Lin
 */
@Component
@Aspect
public class MyLogAspect {

    @Around("@annotation(com.mali.travelstrategy.annotation.MyLog)")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--------------进入环切-----------");

        Object obj = null;

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        String addr = request.getRemoteAddr();
        String uri = request.getRequestURI();
        Object[] args = joinPoint.getArgs();



        System.out.println("请求地址是-----" + addr);
        System.out.println("请求URL是-----" + uri);

        for (Object arg : args) {
            System.out.println("请求参数是-----" + arg.toString());
        }

        // 放行 执行中间的任务
        obj =  joinPoint.proceed();

        System.out.println("业务执行完成-----");
        System.out.println("返回的数据是----" + JSON.toJSON(obj));

        // 环切结束返回
        return obj;
    }



//    @Before()

    /**
     * 先创建一个空方法，方法名随意，但是需要制定@annotation为刚刚自定义的注解
     * 用于声明切入点
     */
    @Pointcut("@annotation(com.mali.travelstrategy.annotation.MyLog)")
    public void myLog() {}

    /**
     * 设置 前置
     * 强调@annotation中的值，需要和方法参数名相同
     * @param arg
     */
    @Before(value = "myLog() && @annotation(arg)", argNames = "arg")
    public void beforeLog(MyLog arg) {
        System.out.println("before----------");
        System.out.println("当前这个注解的参数 title 是： " + arg.title());
        System.out.println("当前这个注解的参数 data 是： " + arg.data());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();;
        String addr = request.getRemoteAddr();
        String uri = request.getRequestURI();

        System.out.println("请求地址是-----" + addr);
        System.out.println("请求URL是-----" + uri);
    }
}
