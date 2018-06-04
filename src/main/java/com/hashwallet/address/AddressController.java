package com.hashwallet.address;

import com.hashwallet.foundation.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-06-04
 *  Time: 下午4:10
 *  Description:
 **/
@Api(tags = "比特币地址")
@RequestMapping(value = "/api/v1/bitcoin")
@RestController
public class AddressController {

    @Resource
    private AddressService addressService;

    @ApiOperation(value = "生成比特币地址", notes = "生成比特币地址的接口")
    @RequestMapping(path = "/address", method = RequestMethod.GET)
    public Response generate() {
        return addressService.generate();
    }

}
