package com.nowcoder.community.controller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 示范
 * AOP 方面组件 用于处理 异常捕捉
 */
//@Component
//@Aspect
public class AlphaAspect {

    /**
     * @Pointcut() 描述那些 bean 是要处理的目标
     * 括号里的意思是 *返回值  包名*.* 下面的所有方法   .. 所有的参数   都要处理
     */
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointCut(){

    }

    /**
     * 在连接点开始的时候 生效
     */
    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }

    /**
     * 在连接点之后的时候 生效
     */
    @After("pointCut()")
    public void after() {
        System.out.println("after");
    }

    /**
     * 在有返回值之前处理
     */
    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    /**
     * 在抛异常的时候 处理
     */
    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }
    /**
     * 在连接点之前和之后都处理
     * 参数 ProceedingJoinPoint 连接点
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 在调目标对象方法的之前
        System.out.println("around-before");

        // 调目标对象被处理的方法
        Object obj =  point.proceed();
        // 在调目标对象方法的之后

        System.out.println("around-after");
        return obj;
    }
}
