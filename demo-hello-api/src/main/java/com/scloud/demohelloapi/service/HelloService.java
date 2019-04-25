package com.scloud.demohelloapi.service;

import com.scloud.demohelloapi.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping(value = "hello4")
    String hello(@RequestParam String name);


    @RequestMapping(value = "hello5")
    User hello(@RequestHeader String name, @RequestHeader Integer age);

    @RequestMapping(value = "hello6")
    String hello(@RequestBody User user);
}
