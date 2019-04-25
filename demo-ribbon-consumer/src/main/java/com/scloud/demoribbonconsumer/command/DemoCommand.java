package com.scloud.demoribbonconsumer.command;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.scloud.demoribbonconsumer.entity.User;
import org.springframework.web.client.RestTemplate;

public class DemoCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    public DemoCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    public DemoCommand() {
//        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName")).andCommandKey(HystrixCommandKey.Factory.asKey("CommandName")));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("commandGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("commandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("threadPoolKey")));
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://localhost:9002/users/{1}",User.class,id);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    public static void flushCache(Long id){
        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("commandKey"),HystrixConcurrencyStrategyDefault.getInstance())
                .clear(String.valueOf(id));
    }
}
