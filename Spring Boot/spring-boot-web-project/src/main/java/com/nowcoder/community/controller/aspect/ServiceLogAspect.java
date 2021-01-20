package com.nowcoder.community.controller.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * log 异常拦截 切面
 */
@Component
@Aspect
public class ServiceLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * @Pointcut() 描述那些 bean 是要处理的目标
     * 括号里的意思是 *返回值  包名*.* 下面的所有方法   .. 所有的参数   都要处理
     */
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointCut(){

    }

    /**
     * 在连接点开始的时候 生效
     * 参数： 连接点  JoinPoint
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        // 格式： 用户 [1,2,3,4] 在 [xxxx] 访问了[com.nowcoder.community.service]
        // 1.获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();
        // 2.通过 request 对象获取 ip地址
        String ip = request.getRemoteHost();
        // 3.获取当前时间
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 4.获取 访问的方法名
        String serviceName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        logger.info(String.format("用户[%s],在[%s],访问了[%s].",ip,now,serviceName));
    }

}
