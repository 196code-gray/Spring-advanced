package com.spring.advanced.trace.template;

import com.spring.advanced.trace.template.code.AbstractTemplate;
import com.spring.advanced.trace.template.code.SubClassLogic1;
import com.spring.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    /**
     * @memo 템플릿 패턴의 필요성
     * 현재 logic1과 2는 log.info("비즈니스 로직1 실행"); 부분을 제외하고 모든 부분이 동일
     * 변하는 부분과 변하지 않는 부분을 분리할 필요 o
     * <p> AbstractTemplate을 사용해서 변하지 않는 부분(공통로직)을 추상적으로 만들고
     * 해당 로직을 사용하고 싶은 하위 클래스에서 재정의하게 분리.
     * <p> 만약 log.info("resultTime={}", result); 부분을 result가 아닌 총 시간 이렇게 변경하게 될 경우 2번 변경 일어남.
     * 반면 AbstractTemplate을 사용하게 되면 1번만 변경하면 됨.
     **/
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직1 실행");

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime={}", result);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직2 실행");

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime={}", result);
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();

    }
}
