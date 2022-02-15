package com.example.springcoreadvanced.proxy.postprocessor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

class BasicTest {
    @Test
    fun basicConfig() {
        val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)

        // A는 빈으로 등록된다.
        val a = applicationContext.getBean("beanA", A::class.java)
        a.helloA()

        // B는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
            applicationContext.getBean(B::class.java)
        }
    }
}

class BasicConfig {
    @Bean(name = ["beanA"])
    fun a() = A()
}

class A {
    fun helloA() = println("hello A")
}

class B {
    fun helloB() = println("hello B")
}
