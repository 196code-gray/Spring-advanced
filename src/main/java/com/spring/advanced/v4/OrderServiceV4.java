package com.spring.advanced.v4;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    // 아이템을 주문하는 로직
    public void orderItem( String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId); // 핵심 코드
                return null;
            }
        };
        template.execute("OrderService.orderItem()");
    }
}
