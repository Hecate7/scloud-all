package com.scloud.demofeignconsumer;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DemoFeignConsumerApplication {

    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoFeignConsumerApplication.class, args);
    }

}
