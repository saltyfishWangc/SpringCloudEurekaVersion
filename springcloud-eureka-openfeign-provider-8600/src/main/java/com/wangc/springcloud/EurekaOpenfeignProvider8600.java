package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author
 * @Description:
 * @date 2022/4/20 11:59
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaOpenfeignProvider8600 {

    public static void main(String... args) {
        SpringApplication.run(EurekaOpenfeignProvider8600.class, args);
    }
}
