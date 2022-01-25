package com.example.springcoreadvanced.proxy.jdkdynamic;

import com.example.springcoreadvanced.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler);
        proxy.call();

        // class com.example.springcoreadvanced.proxy.jdkdynamic.code.AImpl
        log.info("targetClass = {}", target.getClass());
        // class com.sun.proxy.$Proxy11
        log.info("proxyClass = {}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class}, // 프록시 구현에 필요한 인터페이스 리스트 (다중 인터페이스 구조일 수 있으므로)
                handler); // 공통로직
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}