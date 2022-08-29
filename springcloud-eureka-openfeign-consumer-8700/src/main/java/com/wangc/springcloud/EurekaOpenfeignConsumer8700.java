package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author
 * @Description:
 * @date 2022/4/20 14:38
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EurekaOpenfeignConsumer8700 {

    public static void main(String... args) {
        SpringApplication.run(EurekaOpenfeignConsumer8700.class, args);
    }
}
