package com.spring.advanced.v5;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.trace.callback.TraceTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    // 아이템을 주문하는 로직
    public void orderItem( String itemId){
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
