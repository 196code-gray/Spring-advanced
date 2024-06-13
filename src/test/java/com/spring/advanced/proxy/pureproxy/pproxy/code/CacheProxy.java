package com.spring.advanced.proxy.pureproxy.pproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

    private Subject target; // proxy의 진짜 객체
    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheValue == null){
            cacheValue = target.operation();
        }
        return cacheValue;
    }
}
