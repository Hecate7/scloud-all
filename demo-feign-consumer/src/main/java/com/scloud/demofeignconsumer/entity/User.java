package com.scloud.demofeignconsumer.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private Integer id;
    private String userName;
    private String name;
    private int age;
    private BigDecimal balance;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
