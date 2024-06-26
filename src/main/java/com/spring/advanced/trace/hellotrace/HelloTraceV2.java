package com.spring.advanced.trace.hellotrace;

import com.spring.advanced.trace.TraceId;
import com.spring.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<x-";

    // 로그 시작시 호출
    public TraceStatus begin(String message){
        TraceId traceId = new TraceId();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    // 다음 로그 호출 때 레벨 증가
    public TraceStatus beginSync(TraceId beforeTraceId, String message){
        TraceId nextId = beforeTraceId.createNextId(); // Id는 유지, Level + 1
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", nextId.getId(), addSpace(START_PREFIX, nextId.getLevel()), message);
        return new TraceStatus(nextId, startTimeMs, message);
    }

    // 로그 종료시 호출
    public void end(TraceStatus status) {
        complete(status, null);
    }

    // 예외 발생시 호출
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e){
        Long stopTime = System.currentTimeMillis();
        long resultTime = stopTime - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                status.getMessage(), resultTime);
        else log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),
                status.getMessage(), resultTime, e.toString());
    }

    /*
    로그 레벨에 따른 깊이를 만드는 메서드
        ex) START_PREFIX
        level 1 = |-->
        level 2 = |    |-->
     */
    private static String addSpace(String prefix, int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++){
            sb.append((i == level -1) ? "|" + prefix : "|   ");
        }

        return sb.toString();
    }
}
