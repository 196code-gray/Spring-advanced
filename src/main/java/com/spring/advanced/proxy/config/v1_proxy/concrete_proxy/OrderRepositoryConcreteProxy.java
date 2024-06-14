package com.spring.advanced.proxy.config.v1_proxy.concrete_proxy;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.proxy.app.v2.OrderRepositoryV2;
import com.spring.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.request()");
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

}
