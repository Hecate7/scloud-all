package com.scloud.demofeignconsumer.service;

import com.scloud.demofeignconsumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * configuration = DisableHystrixConfiguration.class, 该配置关闭Hystrix支持
 */
@FeignClient(name = "demo-hello", fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "hello3", method = RequestMethod.GET)
    String hello(@RequestBody User user);
}
