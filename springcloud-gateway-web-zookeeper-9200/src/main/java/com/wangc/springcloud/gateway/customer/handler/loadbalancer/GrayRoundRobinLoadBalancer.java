package com.wangc.springcloud.gateway.customer.handler.loadbalancer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.data.redis.core.StringRedisTemplate;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author
 * @Description: 针对灰度上线的负载均衡策略
 *  1.将新版本上线的服务器ip存于redis中 hset ${serviceId}_GRAP_IP ip 172.30.1.114,172.30.60.129
 *  2.取出step 1中的ip
 *  3.从当前服务可用的服务实例中根据host将存在于step 2中的服务实例剔除掉
 *  4.从step 3得到的服务实例中按照轮询策略进行负载均衡
 * @date 2022/8/26 14:29
 */
@Slf4j
public class GrayRoundRobinLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    /**
     * 灰度上线的服务器ip在redis中对应的key
     */
    private final String GRAY_IP_SUFFIX = "_GRAY_IP";

    private static final AtomicInteger position = new AtomicInteger(new Random().nextInt(1000));
    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierObjectProvider;
    private final String serviceId;
    private StringRedisTemplate redisTemplate;

    public GrayRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierObjectProvider, String serviceId) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierObjectProvider = serviceInstanceListSupplierObjectProvider;
    }

    public GrayRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierObjectProvider, String serviceId, StringRedisTemplate redisTemplate) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierObjectProvider = serviceInstanceListSupplierObjectProvider;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierObjectProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get().next().map(this::select);
    }

    private Response<ServiceInstance> select(List<ServiceInstance> instances) {
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }

        // 定义变量表示是否灰度
        boolean isGray = redisTemplate.hasKey(serviceId + GRAY_IP_SUFFIX);
        if (isGray) {
            log.info("服务：{}当前是灰度上线", serviceId);
            /**
             * {
             *  "host":"172.30.1.114",
             *  "instanceId":"4519c483-88ca-4e4f-a037-f7e46d3f2146",
             *  "metadata":{},
             *  "port":8089,
             *  "secure":false,
             *  "serviceId":"xd-service",
             *  "serviceInstance": {
             *      "address":"172.30.1.114",
             *      "enabled":true,
             *      "id":"4519c483-88ca-4e4f-a037-f7e46d3f2146",
             *      "name":"xd-service",
             *      "payload": {
             *          "id":"application-1",
             *          "metadata": {
             *              "$ref":"$.metadata"
             *           },
             *           "name":"xd-service"
             *       },
             *       "port":8089,
             *       "registrationTimeUTC":1661485460241,
             *       "serviceType":"DYNAMIC",
             *       "uriSpec": {
             *          "parts":[
             *              {
             *                  "value":"scheme",
             *                  "variable":true
             *               },
             *               {
             *                  "value":"://",
             *                  "variable":false
             *               },
             *               {
             *                  "value":"address",
             *                  "variable":true
             *               },
             *               {
             *                  "value":":",
             *                  "variable":false
             *               },
             *               {
             *                  "value":"port",
             *                  "variable":true
             *               }
             *           ]
             *        }
             *     },
             *     "uri":"http://172.30.1.114:8089"
             *  }
             */
            /**
             * 1.从redis缓存中获取指定的灰度上线的服务器ip
             * 2.从可用的服务实例列表中去掉step 1中的ip
             * 3.针对step2中的服务实例做轮询(这里为什么不直接取剩下的，因为后面要是加了机器，剩下的就不止1个了，而是大于1个)
             */
            String ips = String.valueOf(redisTemplate.opsForHash().values(serviceId + GRAY_IP_SUFFIX).get(0));
            log.info("服务：{}配置的灰度host:{}", serviceId, ips);
            instances = instances.stream().filter(instance -> !ips.contains(instance.getHost())).collect(Collectors.toList());
        }

        log.info("服务实例信息：{}", JSON.toJSONString(instances));

        int pos = Math.abs(this.position.incrementAndGet());
        ServiceInstance instance = instances.get(pos % instances.size());

        return new DefaultResponse(instance);
    }
}
