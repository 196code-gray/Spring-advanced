package com.spring.advanced.v3;

import com.spring.advanced.logtrace.LogTrace;
import com.spring.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save (String itemId){
        TraceStatus status = null;
        try {
            // controller -> service -> repository 총 3 깊이.
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
