package com.wangc.springcloud.controller;

import com.wangc.springcloud.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ProductConsumerController {

    @Autowired
    RestTemplate restTemplate;

    public static String url = "http://localhost:8400/";

    @GetMapping("product/consumer/get/{id}")
    public Result selectById(@PathVariable("id") Long id) {
        log.info(id + "是我干的");
        return new Result(200, "查询成功", restTemplate.getForObject(url + "product/provider/get/" + id, Result.class));
    }
}
