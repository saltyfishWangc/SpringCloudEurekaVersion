package com.wangc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @Description:
 * @date 2022/5/11 17:22
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${config.msg}")
    private String configMsg;

    @GetMapping("configInfo")
    public String getConfigInfo() {
        return configInfo;
    }

    @GetMapping("configMsg")
    public String getConfigMsg() {
        return configMsg;
    }
}
