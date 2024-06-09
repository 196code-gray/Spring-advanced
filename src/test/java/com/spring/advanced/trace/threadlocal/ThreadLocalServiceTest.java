package com.spring.advanced.trace.threadlocal;

import com.spring.advanced.trace.threadlocal.code.FieldService;
import com.spring.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    /*
    FieldServiceTest와는 다르게 ThreadLocal을 사용하여 각각의 thread마다 다른 저장소에 저장했다.
    ThreadLocal을 사용하면서 userA와 userB가 동시에 접근해도 조회가 잘 되는 것을 확인.
    동시성 문제가 해결되었다.

    ThreadLocal은 thread마다 저장공간을 따로 사용하기 때문에
    thread를 사용완료한 후에는 반드시 remove를 해줘야한다.
     */
    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> service.logic("userA");
        Runnable userB = () -> service.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
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
