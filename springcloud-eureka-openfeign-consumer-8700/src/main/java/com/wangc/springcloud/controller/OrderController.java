package com.wangc.springcloud.controller;

import com.wangc.springcloud.dto.Result;
import com.wangc.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @Description:
 * @date 2022/4/20 15:27
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/get/{id}")
    public Result queryOrderNameById(@PathVariable("id") Integer id) {
        log.info("获取请求参数id：{}", id);
        return new Result(200, "", orderService.queryNameById(id));
    }
}
