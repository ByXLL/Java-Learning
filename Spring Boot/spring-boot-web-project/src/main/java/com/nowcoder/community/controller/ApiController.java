package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiController {

    protected ApiResult Successed(String msg){
        return new ApiResult(200,msg);
    }
    protected ApiResult Successed(String msg, Object data){
        return new ApiResult(200,msg,data);
    }

    protected ApiResult Error(String msg){
        return new ApiResult(400,msg);
    }
}
