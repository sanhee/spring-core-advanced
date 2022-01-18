package com.example.springcoreadvanced.proxy.pureproxy.decorator.code;

public abstract class Decorator implements Component{
    private Component component;

    protected Decorator(Component component){
        this.component = component;
    }

    @Override
    public String operation() {
        return component.operation();
    }
}
