//package com.scloud.demofeignconsumer.common.config;
//
//import feign.Feign;
//import feign.Logger;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//@Configuration
//public class DisableHystrixConfiguration {
//
//    /**
//     * 使用@Scope("prototype")注解为指定的客户端配置Feign.Builder实例
//     * @return
//     */
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
//
//    /**
//     * 调整日志级别
//     * @return
//     */
//    @Bean
//    public Logger.Level feignLoggerLevel(){
//        return Logger.Level.FULL;
//    }
//}
