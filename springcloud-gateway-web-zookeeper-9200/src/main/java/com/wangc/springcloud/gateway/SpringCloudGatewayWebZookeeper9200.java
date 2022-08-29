package com.wangc.springcloud.gateway;

import com.wangc.springcloud.gateway.config.LoadBalanceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;

/**
 * @author
 * @Description:
 * @date 2022/8/25 15:53
 */
@SpringBootApplication
@EnableDiscoveryClient
// 给指定的服务指定负载均衡策略
@LoadBalancerClients(
        @LoadBalancerClient(name = "xd-service", configuration = LoadBalanceConfig.class)
)
public class SpringCloudGatewayWebZookeeper9200 {

    public static void main(String... args) {
        SpringApplication.run(SpringCloudGatewayWebZookeeper9200.class, args);
    }
}
