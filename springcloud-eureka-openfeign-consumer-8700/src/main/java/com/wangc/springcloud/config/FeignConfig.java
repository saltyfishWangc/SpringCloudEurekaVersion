package com.wangc.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @Description: Feign配置
 * @date 2022/4/24 14:12
 */
@Configuration
public class FeignConfig {

    /**
     * FeignClient配置日志级别
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        /**
         * NONE:默认的，不显示任何日志
         * BASIC:仅记录请求方法、URL、响应状态及执行时间
         * HEADERS:除了BASIC中定义的信息之外、还有请求和响应的信息
         * FULL:除了BASIC中定义的信息之外，还有请求和响应的正文及元数据
         */
        return Logger.Level.FULL;
    }
}
