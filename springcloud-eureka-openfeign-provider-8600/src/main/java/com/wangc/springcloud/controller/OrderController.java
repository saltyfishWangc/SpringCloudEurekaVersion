package com.wangc.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @Description:
 * @date 2022/4/20 15:35
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @RequestMapping("/get/{id}")
    public String queryNameById(@PathVariable("id") Integer id) throws Exception {
        Thread.sleep(3000);
        log.info("收到请求参数id：{}", id);
        return "wangc";
    }
}
