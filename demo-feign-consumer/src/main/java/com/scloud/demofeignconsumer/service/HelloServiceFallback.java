package com.scloud.demofeignconsumer.service;

import com.scloud.demofeignconsumer.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("Error",0);
    }

    @Override
    public String hello(User user) {
        return "error";
    }
}
