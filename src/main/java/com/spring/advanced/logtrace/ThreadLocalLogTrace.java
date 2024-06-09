package com.spring.advanced.logtrace;

import com.spring.advanced.trace.TraceId;
import com.spring.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<x-";

    // Id를 필드에 두고 계속 사용.
    private ThreadLocal<TraceId> traceHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceHolder.get();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId(){
        TraceId traceId = traceHolder.get();
        if (traceId == null){
            traceHolder.set(new TraceId());
        } else {
            traceHolder.set(traceId.createNextId());
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
        TraceId traceId = traceHolder.get();
        if (traceId.isFirstLevel()){
            traceHolder.remove();
        } else {
            traceHolder.set(traceId.createPreviousId());
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
