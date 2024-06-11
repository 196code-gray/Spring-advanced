package com.spring.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {
/*
전략패턴을 사용한 방식.
현재 변하지 않는 부분을 ContextV1으로 구현해주면서 Strategy interface에만 의존.
템플릿 메서드 패턴은 상속을 받기 때문에 불필요한 메서드들도 포함해야 했다.
그리고 템플릿 메서드 패턴은 상위 클래스에 변경이 생길 경우 하위 클래스도 변경이 일어남.
반면 전략패턴은 Strategy에 변경이 일어나지 않는 이상 구현체에 변경이 일어날 일 x
현재 execute에 변경이 일어나도 Strategy나 Strategy를 implement한 클래스에서도 변경 x
 */
    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();

        strategy.call(); // 핵심 로직 위임

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime={}", result);
    }
}
