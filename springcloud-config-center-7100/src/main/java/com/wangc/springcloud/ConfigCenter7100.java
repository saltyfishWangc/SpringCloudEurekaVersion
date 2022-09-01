package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author
 * @Description:
 * @date 2022/5/10 19:39
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigCenter7100 {

    public static void main(String... args) {
        SpringApplication.run(ConfigCenter7100.class, args);
    }
}
