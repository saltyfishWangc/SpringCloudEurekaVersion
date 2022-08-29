package com.wangc.springcloud.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * 定义限流器的key-resolver
 */
@Configuration
@Slf4j
public class ResolverConfiguration {

    /**
     * Description:

     Parameter 1 of method requestRateLimiterGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration required a single bean, but 3 were found:
     - userKeyResolver: defined by method 'userKeyResolver' in class path resource [com/wangc/springcloud/resolver/ResolverConfiguration.class]
     - ipKeyResolver: defined by method 'ipKeyResolver' in class path resource [com/wangc/springcloud/resolver/ResolverConfiguration.class]
     - uriKeyResolver: defined by method 'uriKeyResolver' in class path resource [com/wangc/springcloud/resolver/ResolverConfiguration.class]


     Action:

     Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed

     注意：如果我们@Bean配置了多个KeyResolver，那么在初始化时会报发现了多个。
          可以在不注释代码的情况下保证只有一个KeyResovler被容器加载，那就是在我们想使用的@Bean上加上@Primary
     * @return
     */

    @Bean
//    @Primary
    KeyResolver userKeyResolver() {
        /**
         * 这种限流器要求请求传参一定要有user参数，如果没有，是会触发异常的
         * 如：
         * curl http://127.0.0.1:9100/test/product/consumer/get/1?user  这样正常访问
         * curl http://127.0.0.1:9100/test/product/consumer/get/1 是无法请求的会报异常，如果配置了熔断机制，会转发到熔断的请求/fallbackA进行返回
         */
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//        return exchange -> Mono.just("1");
        return exchange -> {
            String user = exchange.getRequest().getQueryParams().getFirst("user");
            log.info("userKeyResolver 限流规则 user：{}", user);
            return Mono.just(user);
        };
    }

    /**
     * 根据请求的ip限流
     * @return
     */
    @Bean
    KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        return exchange -> {
            String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            log.info("ipKeyResolver 限流规则 ip：{}", hostAddress);
            return Mono.just(hostAddress);
        };
    }

    /**
     * 根据接口的URI进行限流，获取请求地址的uri作为限流key
     * @return
     */
    @Bean
    @Primary
    KeyResolver uriKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getPath().value());
        return exchange -> {
            String uri = exchange.getRequest().getPath().value();
            log.info("uriKeyResolver 限流规则 uri：{}", uri);
            return Mono.just(uri);
        };
    }

    /**
     * 按照 path 限流
     * @return
     */
    @Bean(value = "pathKeyResolver")
    public KeyResolver pathKeyResolver() {
        return exchange -> {
            Route route = (Route) exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            log.info("pathKeyResolver 限流规则 routeId：{}", route.getId());
            return Mono.just(exchange.getRequest().getPath().toString());
        };
    }
}
