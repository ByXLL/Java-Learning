package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    protected ApiResult Successed(String msg){
        return new ApiResult(200,msg);
    }

    protected ApiResult Error(String msg){
        return new ApiResult(400,msg);
    }
}
