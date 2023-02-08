package com.brodog.springframework.beans;

/**
 * bean定义异常
 * @author By-BroDog
 * @createTime 2023-02-02
 */
public class BeansException extends RuntimeException{
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
