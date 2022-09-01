package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author
 * @Description:
 * @date 2022/5/11 17:21
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClient7200 {

    public static void main(String... args) {
        SpringApplication.run(ConfigClient7200.class, args);
    }
}
