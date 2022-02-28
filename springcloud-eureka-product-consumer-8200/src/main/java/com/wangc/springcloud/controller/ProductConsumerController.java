package com.wangc.springcloud.controller;

import com.wangc.springcloud.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class ProductConsumerController {

    @Autowired
    RestTemplate restTemplate;

    public static String url = "http://localhost:8100/";

    @GetMapping("product/consumer/get/{id}")
    public Result selectById(@PathVariable("id") Long id) {
        return new Result(200, "查询成功", restTemplate.getForObject(url + "product/provider/get/" + id, Result.class));
    }
}
