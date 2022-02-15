package com.example.springcoreadvanced.proxy.postprocessor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

class BeanPostProcessorTest {

    @Test
    fun `빈 후처리기를 통해 빈A를 빈B로 바꿔치기 한다`() {
        val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)

        //beanA 이름으로 B 객체가 빈으로 등록된다.
        val b = applicationContext.getBean("beanA", B2::class.java)
        b.helloB();

        // A는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
            applicationContext.getBean(A::class.java)
        }
    }
}

class BeanPostProcessorConfig {
    @Bean(name = ["beanA"])
    fun a() = A2()

    @Bean
    fun helloPostProcessor() = AToBPostProcessor()
}

class A2 {
    fun helloA() = println("hello A")
}

class B2 {
    fun helloB() = println("hello B")
}

class AToBPostProcessor : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? = when (bean) {
        is A2 -> B2()
        else -> bean
    }
}
