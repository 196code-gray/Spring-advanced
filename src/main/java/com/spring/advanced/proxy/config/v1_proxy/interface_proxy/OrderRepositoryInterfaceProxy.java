package com.spring.advanced.proxy.config.v1_proxy.interface_proxy;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.proxy.app.v1.OrderRepositoryV1;
import com.spring.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace logTrace;

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
