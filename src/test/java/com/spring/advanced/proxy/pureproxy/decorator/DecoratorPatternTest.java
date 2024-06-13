package com.spring.advanced.proxy.pureproxy.decorator;

import com.spring.advanced.proxy.pureproxy.decorator.code.Component;
import com.spring.advanced.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import com.spring.advanced.proxy.pureproxy.decorator.code.MessageDecorator;
import com.spring.advanced.proxy.pureproxy.decorator.code.RealComponent;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecorator(){
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1(){
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }
}
