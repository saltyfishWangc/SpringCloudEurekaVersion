package com.wangc.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author
 * @Description:
 * @date 2022/7/12 11:38
 */
//@SpringBootApplication(exclude = {ServletWebServerApplicationContext.class})
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudGatewayWebEureka9102 {

    public static void main(String... args) {
        SpringApplication.run(SpringCloudGatewayWebEureka9102.class, args);
    }
}
