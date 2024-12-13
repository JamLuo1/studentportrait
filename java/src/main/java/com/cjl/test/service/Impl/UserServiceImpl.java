package com.cjl.test.service.Impl;

import com.cjl.test.mapper.UserMapper;
import com.cjl.test.pojo.User;
import com.cjl.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
