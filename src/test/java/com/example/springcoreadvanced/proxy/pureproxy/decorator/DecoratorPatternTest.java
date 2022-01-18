package com.example.springcoreadvanced.proxy.pureproxy.decorator;

import com.example.springcoreadvanced.proxy.pureproxy.decorator.code.Component;
import com.example.springcoreadvanced.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import com.example.springcoreadvanced.proxy.pureproxy.decorator.code.MessageDecorator;
import com.example.springcoreadvanced.proxy.pureproxy.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {
    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }
}
