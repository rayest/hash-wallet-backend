package com.hashwallet.keys;


import com.hashwallet.foundation.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "密钥对")
@RequestMapping(value = "/api/v1/pairKey")
@RestController
public class PairKeyController {

    @Resource
    private PairKeyService pairKeyService;

    @ApiOperation(value = "密钥对", notes = "生成密钥对的接口")
    @GetMapping("/keys")
    public Response generate() {
        PairKey pairKey = pairKeyService.generate();
        return new Response(pairKey);
    }
}
