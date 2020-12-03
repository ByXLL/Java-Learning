package com.example.springboot.controller;



import com.example.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/***
 * 异常处理器
 */

@ControllerAdvice
public class MyExceptionHandler {
    // 处理我们定义的异常

    // 浏览器和客户端都是返回json
//    @ResponseBody
//    @ExceptionHandler(value = UserNotExistException.class)
//    public Map<String, Object> handleException(Exception exception) {
//        // 当监听到这个我们定义的异常 就会将异常数据传递进来
//
//        System.out.println("监听到异常了");
//        Map<String, Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",exception.getMessage());
//        return map;
//    }

    @ExceptionHandler(value = UserNotExistException.class)
    public String handleException(Exception exception, HttpServletRequest request) {
        // 当监听到这个我们定义的异常 就会将异常数据传递进来

        System.out.println("监听到异常了");
        Map<String, Object> map = new HashMap<>();

        // 传入我们的错误状态码
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,500);
        map.put("code","0");
        map.put("message",exception.getMessage());

        // 将额外添加的信息添加到一个属性中
        request.setAttribute("ext",map);
        return "forward:/error";
    }

}
