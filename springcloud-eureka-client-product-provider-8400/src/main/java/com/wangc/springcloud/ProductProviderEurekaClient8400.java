package com.wangc.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wangc.springcloud.dao")
public class ProductProviderEurekaClient8400 {

    public static void main(String... args) {
        SpringApplication.run(ProductProviderEurekaClient8400.class, args);
    }
}
