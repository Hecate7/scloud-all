package com.scloud.demoribbonconsumer.service;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.scloud.demoribbonconsumer.command.DemoCommand;
import com.scloud.demoribbonconsumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack",ignoreExceptions = {HystrixBadRequestException.class})
//    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    public String helloService(@CacheKey("id") User user){
        /* user = new DemoCommand(
                com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(50000)
                ), new RestTemplate(),1L).execute();*/
        /*Future<User> futureUser = new DemoCommand(
                com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(50000)
                ), new RestTemplate(),1L).queue();*/
        /*Future<User> userFuture = new AsyncResult<User>(){
            public  User invoke(){
                return restTemplate.getForObject("http://demo-user/users/{1}",User.class,1);
            }
        };*/

        return restTemplate.getForObject("http://demo-user/users/{1}",User.class,user.getId()).toString();
    }

    private String helloFallBack(){
        return "This is error";
    }

    private Long getUserByIdCacheKey(Long id){
        return id;
    }
}
