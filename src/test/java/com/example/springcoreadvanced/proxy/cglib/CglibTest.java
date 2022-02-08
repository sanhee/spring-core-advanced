package com.example.springcoreadvanced.proxy.cglib;

import com.example.springcoreadvanced.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();

        // .......service.ConcreteService
        log.info("targetClass={}", target.getClass());
        // .......service.ConcreteService$$EnhancerByCGLIB$$e21ca0dc
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
