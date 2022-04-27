package com.brodog.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.brodog.aop.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    /**
     * 先创建一个空方法，方法名随意，但是需要制定@annotation为刚刚自定义的注解
     * 用于声明切入点
     */
    @Pointcut("@annotation(com.brodog.aop.annotation.MyLog)")
    public void myLog() {}

    /**
     * 设置 前置
     * myLog() 就是之前声明切入点的空方法
     * 强调 @annotation中的值，需要和方法参数名相同
     * @param arg   自定义注解实体
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


    /**
     * 设置 前置
     * myLog() 就是之前声明切入点的空方法
     * 强调 @annotation中的值，需要和方法参数名相同
     */
    @After("myLog()")
    public void afterLog(JoinPoint joinPoint) {
        System.out.println("After----------");
        System.out.println("after 返回的数据是----" + JSON.toJSON( joinPoint.getArgs()));
    }




    /**
     * 环切
     * @param joinPoint     贯穿环切点的实体
     * @return              环切点（方法）执行完成返回的结果
     * @throws Throwable
     */

    @Around("@annotation(com.brodog.aop.annotation.MyLog)")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--------------进入环切-----------");

        Object obj = null;

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String addr = request.getRemoteAddr();
        String uri = request.getRequestURI();
        Object[] args = joinPoint.getArgs();



        System.out.println("请求地址是-----" + addr);
        System.out.println("请求URL是-----" + uri);

        for (Object arg : args) {
            System.out.println("请求参数是-----" + arg.toString());
        }

        // 执行中间的任务
        obj =  joinPoint.proceed();

        System.out.println("业务执行完成-----");
        System.out.println("返回的数据是----" + JSON.toJSON(obj));

        // 环切结束返回  必须要返回
        return obj;
    }

}
