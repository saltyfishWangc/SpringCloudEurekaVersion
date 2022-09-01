package com.wangc.springcloud.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author
 * @Description:
 * @date 2022/7/12 19:50
 */
public interface GatewayDynamicRouteService {

    /**
     * 添加路由
     * @param routeDefinition
     * @return
     */
    int add(RouteDefinition routeDefinition);

    /**
     * 更新路由
     * @param routeDefinition
     * @return
     */
    int update(RouteDefinition routeDefinition);

    /**
     * 删除路由
     * @param id
     * @return
     */
    Mono<ResponseEntity<Object>> delete(String id);
}
