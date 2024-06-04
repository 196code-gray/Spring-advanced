package com.spring.advanced.logtrace;

import com.spring.advanced.trace.TraceId;
import com.spring.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<x-";

    // Id를 필드에 두고 계속 사용.
    private TraceId traceHolder;

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceHolder;
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId(){
        if (traceHolder == null){
            traceHolder = new TraceId();
        } else {
            traceHolder = traceHolder.createNextId();
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
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

        releaseTraceId(); // 3 -> 2 -> 1 줄어든다
    }

    // level이 줄어드는 메서드
    private void releaseTraceId(){
        if (traceHolder.isFirstLevel()){
            traceHolder = null;
        } else {
            traceHolder = traceHolder.createPreviousId();
        }
    }

    private static String addSpace(String prefix, int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++){
            sb.append((i == level -1) ? "|" + prefix : "|   ");
        }

        return sb.toString();
    }

}
