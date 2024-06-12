package com.spring.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(CallBack callBack) {
        long startTime = System.currentTimeMillis();

        callBack.call(); // 핵심 로직 위임

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime={}", result);
    }

}
