package com.scloud.demouser.controller;

import com.scloud.demouser.entity.User;
import com.scloud.demouser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/")
    public List<User> findByIds(@RequestBody String ids){
        return userService.findByIds(ids);
    }
}
