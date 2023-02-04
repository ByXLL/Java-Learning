package com.mali.travelstrategy.controller;

import com.mali.travelstrategy.annotation.PassTokenRequired;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import com.mali.travelstrategy.service.impl.TestTransactionalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author By-Lin
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestTransactionalServiceImpl testTransactionalService;

    @PassTokenRequired
    @RequestMapping("/startBatchInsert")
    public ApiResult startBatchInsert() {
        testTransactionalService.testBatchInsert();
        return new ApiResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getDesc());
    }

    @PassTokenRequired
    @RequestMapping("/testChildTransaction")
    public ApiResult testChildTransaction() {
        testTransactionalService.testChildTransaction();
        return new ApiResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getDesc());
    }
}
