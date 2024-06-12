package com.spring.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {
/*
전략을 파라미터로 전달 받는 방식
 */
    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();

        strategy.call(); // 핵심 로직 위임

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime={}", result);
    }
}
