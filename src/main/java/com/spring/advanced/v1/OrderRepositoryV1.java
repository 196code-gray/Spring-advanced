package com.spring.advanced.v1;

import com.spring.advanced.trace.TraceStatus;
import com.spring.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save (String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            trace.end(status);
            if (itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!!");
            }
            sleep(1000);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
