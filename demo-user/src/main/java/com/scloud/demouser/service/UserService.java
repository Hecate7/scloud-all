package com.scloud.demouser.service;

import com.scloud.demouser.dao.UserMapper;
import com.scloud.demouser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findById(Long id){
        User findOne = userMapper.findOne(id);
        return findOne;
    }

    public List<User> findByIds(String ids){
        List<String> idList = Arrays.asList(ids.split(","));
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            userList.add(findById(Long.valueOf(idList.get(i))));
        }
        return userList;
    }
}
