package com.example.springcoreadvanced.proxy;

import com.example.springcoreadvanced.proxy.config.AppV1Config;
import com.example.springcoreadvanced.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "com.example.springcoreadvanced.proxy.app")
public class ProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }
}
