package com.spring.advanced.v3;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    // 아이템을 주문하는 로직
    public void orderItem( String itemId){

        TraceStatus status = null;
        try {
            // controller에서 깊이가 1개 들어갔으므로 beginSync로 level + 1
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
