package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewYearController extends ApiController {
    @GetMapping("/happy/new/year")
    public ApiResult happyNewYear() {
        System.out.println("Happy New Year");
        return Successed("Happy New Year");
    }
}