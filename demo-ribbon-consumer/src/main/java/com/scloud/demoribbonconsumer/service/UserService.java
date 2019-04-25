package com.scloud.demoribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scloud.demoribbonconsumer.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "finfAll", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public User find(Long id){
        return restTemplate.getForObject("http://demo-user/users/{1}",User.class,id);
    }

    @HystrixCommand
    public List<User> findAll(List<Long> ids){
        return restTemplate.getForObject("http://demo-user/users?ids={1}",List.class,StringUtils.join(ids,"."));
    }
}
