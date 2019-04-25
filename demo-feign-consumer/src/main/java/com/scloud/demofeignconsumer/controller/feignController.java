package com.scloud.demofeignconsumer.controller;

import com.scloud.demofeignconsumer.entity.User;
import com.scloud.demofeignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class feignController {
    @Autowired
    HelloService helloService;
//    @Autowired
//    RefactorService refactorService;

    @RequestMapping("/hello")
    public String helloConsumer(){
        return helloService.hello();
    }

    @RequestMapping("/hello2")
    public String helloConsumer2(){
        StringBuilder builder = new StringBuilder();
        builder.append(helloService.hello()).append("；");
        builder.append(helloService.hello("NAME")).append("；");
        builder.append(helloService.hello("NAME",17)).append("；");
        builder.append(helloService.hello(new User("NAME", 18))).append("；");
        return builder.toString();
    }
//
//    @RequestMapping("/feignConsumer")
//    public String feignConsumer(){
//        StringBuilder builder = new StringBuilder();
//        builder.append(refactorService.hello("NINA")).append("\n");
//        builder.append(refactorService.hello("NINA",17)).append("\n");
//        builder.append(refactorService.hello(new com.scloud.demohelloapi.dto.User("NINA", 18))).append("\n");
//        return builder.toString();
//    }
}
