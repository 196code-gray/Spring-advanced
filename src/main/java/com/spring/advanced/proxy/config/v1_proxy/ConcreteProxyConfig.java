package com.spring.advanced.proxy.config.v1_proxy;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.proxy.app.v2.OrderControllerV2;
import com.spring.advanced.proxy.app.v2.OrderRepositoryV2;
import com.spring.advanced.proxy.app.v2.OrderServiceV2;
import com.spring.advanced.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.spring.advanced.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.spring.advanced.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
        return new OrderRepositoryConcreteProxy(new OrderRepositoryV2(), logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace){
        return new OrderServiceConcreteProxy(new OrderServiceV2(orderRepositoryV2(logTrace)), logTrace);
    }

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace){
        return new OrderControllerConcreteProxy(logTrace, new OrderControllerV2(orderServiceV2(logTrace)));
    }
}
