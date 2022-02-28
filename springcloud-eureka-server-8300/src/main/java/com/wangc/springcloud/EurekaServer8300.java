package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer8300 {

    public static void main(String... args) {
        SpringApplication.run(EurekaServer8300.class, args);
    }
}
