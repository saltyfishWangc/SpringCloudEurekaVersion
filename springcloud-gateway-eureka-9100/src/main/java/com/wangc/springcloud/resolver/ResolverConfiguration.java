package com.wangc.springcloud.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * 定义限流器的key-resolver
 */
@Configuration
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
    @Primary
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
