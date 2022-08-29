package com.wangc.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@ComponentScan
@FeignClient(value = "springcloud-eureka-openfeign-provider-8600")
public interface OrderService {

    @GetMapping("/order/get/{id}")
    String queryNameById(@PathVariable("id") Integer id);
}
