package com.hashwallet.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-06-04
 *  Time: 上午11:53
 *  Description:
 **/
@Api(tags = "测试")
@RequestMapping(value = "/api/v1/test")
@RestController
public class TestController {


    @ApiOperation(value = "测试", notes = "测试 wallet 的接口")
    @GetMapping("/wallet")
    public String test() {
        return "test";
    }
}
