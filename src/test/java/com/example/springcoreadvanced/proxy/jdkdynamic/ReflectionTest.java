package com.example.springcoreadvanced.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 리플렉션 주의

/*
 - 리플렉션 기술은 런타임에 동작하기 때문에, 컴파일 시점에 오류를 잡을 수 없다.
    - ex) getMethod("callA")
 */
@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직 1 - callA
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);

        // 공통 로직 2 - callB
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 메타 정보 획득, 내부 클래스 구분: $
        // FQCN (Fully Qualified Class Name)
        Class<?> classHello = Class.forName("com.example.springcoreadvanced.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // 해당 클래스의 callA 메서드 메타정보(methodCallA)를 획득
        Method methodCallA = classHello.getMethod("callA");
        // 획득한 메서드 메타정보로 실제 인스턴스의 메서드를 호출
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        Class<?> classHello = Class.forName("com.example.springcoreadvanced.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Hello target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("end");
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}


