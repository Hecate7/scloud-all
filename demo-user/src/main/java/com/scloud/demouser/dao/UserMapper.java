package com.scloud.demouser.dao;

import com.scloud.demouser.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findOne(Long id);
}
