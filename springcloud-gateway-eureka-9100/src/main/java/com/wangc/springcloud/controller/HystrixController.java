package com.wangc.springcloud.controller;

import com.wangc.springcloud.dto.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @Description: 熔断降级控制器
 * @date 2022/3/23 20:31
 */
@RestController
public class HystrixController {

    @RequestMapping("/fallbackA")
    public Result fallback() {
        return new Result(500, "服务暂时不可用", null);
    }
}
