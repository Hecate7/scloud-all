//package com.scloud.demohello.web;
//
//import com.scloud.demohelloapi.dto.User;
//import com.scloud.demohelloapi.service.HelloService;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RefactorHelloController implements HelloService {
//    @Override
//    public String hello(String name) {
//        return "Hello " + name;
//    }
//
//    @Override
//    public User hello(String name, Integer age) {
//        return new User(name, age);
//    }
//
//    @Override
//    public String hello(User user) {
//        return "HELLO " + user.getName() + "," + user.getAge();
//    }
//}
