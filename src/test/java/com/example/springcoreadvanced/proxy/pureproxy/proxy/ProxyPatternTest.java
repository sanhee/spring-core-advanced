package com.example.springcoreadvanced.proxy.pureproxy.proxy;

import com.example.springcoreadvanced.proxy.pureproxy.proxy.code.CacheProxy;
import com.example.springcoreadvanced.proxy.pureproxy.proxy.code.ProxyPatternClient;
import com.example.springcoreadvanced.proxy.pureproxy.proxy.code.RealSubject;
import com.example.springcoreadvanced.proxy.pureproxy.proxy.code.Subject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }


    /*
    1. client의 cacheProxy 호출 -> cacheProxy에 캐시 값이 없다 -> realSubject를 호출, 결과를 캐시에
    2. client의 cacheProxy 호출 -> cacheProxy에 캐시 값이 있다. -> cacheProxy에서 즉시 반환 (0초)
    3. client의 cacheProxy 호출 -> cacheProxy에 캐시 값이 있다. -> cacheProxy에서 즉시 반환 (0초)
     */

    @Test
    void cacheProxyTest() {
        Subject realSubject = new RealSubject();
        Subject cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
