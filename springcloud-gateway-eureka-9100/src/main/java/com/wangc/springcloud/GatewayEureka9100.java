package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayEureka9100 {

    public static void main(String... args) {
        SpringApplication.run(GatewayEureka9100.class, args);
    }
}
