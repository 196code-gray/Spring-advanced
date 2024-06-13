package com.spring.advanced.proxy;

import com.spring.advanced.proxy.pureproxy.pproxy.code.ProxyPatternClient;
import com.spring.advanced.proxy.pureproxy.pproxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest(){
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }
}
