package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gateway9000 {

    public static void main(String... args) {
        SpringApplication.run(Gateway9000.class, args);
    }

    /**
     * 自定义路由器
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("costom-route", r -> r.path("/custom")
                .uri("http://localhost:8100"))
                .build();
    }
}
