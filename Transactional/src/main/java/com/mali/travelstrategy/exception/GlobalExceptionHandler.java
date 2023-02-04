package com.mali.travelstrategy.exception;

import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局异常处理
 * @author By-mali
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 自定义登录异常的处理方法
     * @param request   HttpServletRequest
     * @param e         错误异常对象
     * @return          响应数据
     */
    @ExceptionHandler({LoginException.class})
    public ApiResult loginExceptionHandler(HttpServletRequest request, Exception e){
        return new ApiResult(HttpCodeEnum.AUTH_ERROR.getCode(),e.getMessage());
    }


    /**
     * 自定义全局异常的处理方法
     * @param request   HttpServletRequest
     * @param e         错误异常对象
     * @return          响应数据
     */
    @ExceptionHandler({Exception.class})
    public ApiResult exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        log.error("URL:{} ,服务器异常: {}",request.getRequestURI(), e.getMessage());
        return new ApiResult(HttpCodeEnum.ERROR.getCode(),"服务器异常" + e.getMessage(), null);
    }

}
