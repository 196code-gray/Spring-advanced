package com.spring.advanced.proxy.config.v1_proxy;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.proxy.app.v1.*;
import com.spring.advanced.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.spring.advanced.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.spring.advanced.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace){
//        OrderControllerV1Impl controller = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(new OrderControllerV1Impl(orderService(logTrace)), logTrace);
//        return new OrderControllerInterfaceProxy(controller, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace){
        return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepository(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace){
        return new OrderRepositoryInterfaceProxy(new OrderRepositoryV1Impl(), logTrace);
    }
}
