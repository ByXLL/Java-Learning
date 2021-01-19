package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 异常通知类
 * 使用 ControllerAdvice 做声明 当前类为控制器的通知类 参数为扫描的范围
 * Controller.class 代表 只去扫描带有 Controller 注解bean
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(Exception.class);
    /**
     * 异常处理类
     * ExceptionHandler 处理异常注解
     * 参数 {Exception.class} 代表所有的 Exception
     */
    @ExceptionHandler({Exception.class})
    public void handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 当这个方法被执行了 代表捕获到了异常
        logger.error("服务器发生异常："+ exception.getMessage());
        // 记录堆栈信息
        for (StackTraceElement element : exception.getStackTrace()) {
            logger.error(element.toString());
        }

        // 判断请求的类型 判断是Ajax还是页面
        String xRequestedWith = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJsonString(500, "服务器异常!"));
        } else {
            response.sendRedirect(request.getContextPath() + "/error");
        }


    }
}
