package com.wangc.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangc.springcloud.dao")
public class ProductProvider8100 {

    public static void main(String... args) {
        SpringApplication.run(ProductProvider8100.class, args);
    }
}
