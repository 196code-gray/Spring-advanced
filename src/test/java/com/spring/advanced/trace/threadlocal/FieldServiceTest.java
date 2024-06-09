package com.spring.advanced.trace.threadlocal;

import com.spring.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    /*
    현재 userA와 userB가 하나의 nameStore에 접근하여 값을 변경.
    userA가 변경하고 잠시 쉬는 동안 userB가 접근&변경.
    userA가 다시 돌아왔을 때 userA가 아닌 userB가 조회 됨.
    이런 문제를 동시성 문제라고 한다.
     */
    private FieldService fieldService = new FieldService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100); // 동시성 발생 x
        threadB.start();

        sleep(3000);
        log.info("main exit");

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
