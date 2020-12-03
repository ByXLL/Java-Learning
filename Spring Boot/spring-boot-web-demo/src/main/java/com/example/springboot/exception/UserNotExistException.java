package com.example.springboot.exception;

/**
 * 定义一个用户异常
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在---");
    }
}
