package com.spring.advanced.logtrace;

import com.spring.advanced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);  // 로그 시작
    void end(TraceStatus status);  // 로그 끝
    void exception(TraceStatus status, Exception e); // 로그 에러 발생
}
