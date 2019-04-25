package com.scloud.demohello.web;

import com.scloud.demohello.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Value("${from}")
    private String from;

    @RequestMapping(value = "from")
    public String from(){
        return from;
    }

    @RequestMapping(value = "/hello")
    public String index() throws InterruptedException {
        /** 增加随机延迟 */
//        int sleepTime = new Random().nextInt(3000);
//        logger.info("Sleep Time :{}",sleepTime);
//        Thread.sleep(sleepTime);
//        ServiceInstance instance = client.getInstances("DEMO-HELLO").get(0);
//        logger.info("/hello, host: {}, service_id: {}",instance.getHost(),instance.getServiceId());
        return "Hello World!";
    }

    @RequestMapping(value = "hello1")
    public String hello(@RequestParam String name){
        return "hello"+name;
    }

    @RequestMapping(value = "hello2")
    public User hello(@RequestHeader String name, @RequestHeader Integer age){
        return new User(name,age);
    }

    @RequestMapping(value = "hello3")
    public String hello(@RequestBody User user){
        return "HELLO " + user.getName() + "," + user.getAge();
    }
}
