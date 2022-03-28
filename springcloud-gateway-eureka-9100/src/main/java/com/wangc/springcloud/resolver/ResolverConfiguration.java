package com.wangc.springcloud.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 定义限流器的key-resolver
 */
@Configuration
public class ResolverConfiguration {

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    /**
     * 根据请求的ip限流
     * @return
     */
    @Bean
    KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 根据接口的URI进行限流，获取请求地址的uri作为限流key
     * @return
     */
    @Bean
    KeyResolver uriKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
