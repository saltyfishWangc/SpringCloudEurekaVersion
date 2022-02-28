package com.wangc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// 因为这个工程不需要连接数据库，所以pom中没有导入相应依赖，因此需要将自动注入数据源的类过滤掉
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ProductConsumer8200 {

    public static void main(String... args) {
        SpringApplication.run(ProductConsumer8200.class, args);
    }
}
