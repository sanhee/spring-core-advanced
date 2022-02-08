package com.example.springcoreadvanced.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteService {
    public final void call() {
        log.info("ConcreteService 호출");
    }
}