package com.spring.advanced.v2;

import com.spring.advanced.trace.TraceId;
import com.spring.advanced.trace.TraceStatus;
import com.spring.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    // 아이템을 주문하는 로직
    public void orderItem(TraceId traceId, String itemId){

        TraceStatus status = null;
        try {
            // controller에서 깊이가 1개 들어갔으므로 beginSync로 level + 1
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
