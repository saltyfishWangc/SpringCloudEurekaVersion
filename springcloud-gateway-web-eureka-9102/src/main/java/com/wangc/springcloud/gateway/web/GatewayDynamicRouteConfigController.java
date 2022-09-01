package com.wangc.springcloud.gateway.web;

import com.wangc.springcloud.gateway.service.GatewayDynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author
 * @Description:
 * @date 2022/7/12 11:59
 */
@RestController
@RequestMapping("/gatewayDynamicRouteConfig/")
public class GatewayDynamicRouteConfigController {

    @Autowired
    GatewayDynamicRouteService gatewayDynamicRouteService;

    @RequestMapping("add")
    public String add(@RequestBody RouteDefinition entity) {
        int result = gatewayDynamicRouteService.add(entity);
        return String.valueOf(result);
    }

    /**
     * 这里删除之后，下次重启，静态配置里面的是否会加载到redis
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        System.out.println("进来了");
        return gatewayDynamicRouteService.delete(id);
    }

    @RequestMapping("update")
    public String update(@RequestBody RouteDefinition entity) {
        int result = gatewayDynamicRouteService.update(entity);
        return String.valueOf(result);
    }
}
