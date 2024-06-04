package com.spring.advanced.v2;

import com.spring.advanced.trace.TraceId;
import com.spring.advanced.trace.TraceStatus;
import com.spring.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save (TraceId traceId, String itemId){
        TraceStatus status = null;
        try {
            // controller -> service -> repository 총 3 깊이.
            status = trace.beginSync(traceId, "OrderRepository.save()");
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
