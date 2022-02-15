package com.example.springcoreadvanced.proxy.postprocessor

import org.aopalliance.aop.Advice
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean

class BeanPostProcessorTest2 {

    @Test
    fun `빈 후처리기를 통해 Noeul 객체를 프록시로 바꿔치기 한 후 빈을 등록`() {
        val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig2::class.java)

        val noeul = applicationContext.getBean("beanNoeul", Noeul::class.java)
        println(noeul.say());

        val cooper = applicationContext.getBean("beanCooper", Cooper::class.java)
        println(cooper.say());
    }
}

class BeanPostProcessorConfig2 {
    @Bean(name = ["beanNoeul"])
    fun noeul() = Noeul()

    @Bean(name = ["beanCooper"])
    fun cooper() = Cooper()

    @Bean
    fun customPostProcessor() = CustomPostProcessor(CustomAdvice())
}

open class Noeul {
    open fun say() : String = "안녕하세요. 노을입니다."
}

open class Cooper {
    open fun say() : String = "안녕하세요. 쿠퍼입니다."
}

class CustomPostProcessor(private val advice: Advice) : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any?{
        // 프록시 대상이다! 프록시로 만들어서 변환
        if(bean is Noeul){
            val proxyFactory = ProxyFactory(bean)
            proxyFactory.addAdvice(advice)

            val proxy = proxyFactory.getProxy();

            return proxy
        }

        return bean
    }
}

class CustomAdvice : MethodInterceptor {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun invoke(invocation: MethodInvocation): Any? {
        log.info { "Proxy 실행" }
        val result = invocation.proceed() as String
        return result.replace("노을", "산희");
    }
}
