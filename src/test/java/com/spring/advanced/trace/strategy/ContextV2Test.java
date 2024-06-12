package com.spring.advanced.trace.strategy;

import com.spring.advanced.trace.strategy.code.strategy.ContextV2;
import com.spring.advanced.trace.strategy.code.strategy.Strategy;
import com.spring.advanced.trace.strategy.code.strategy.StrategyLogic1;
import com.spring.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2(){
        ContextV2 context = new ContextV2();

        // 익명 클래스 버전
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        // 람다 버전
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
