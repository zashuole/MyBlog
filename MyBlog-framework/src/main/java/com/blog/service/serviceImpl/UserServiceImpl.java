package com.blog.service.serviceImpl;

import com.blog.mapper.UserMapper;
import com.blog.pojo.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User getById(Long userId) {
        User user = userMapper.getById(userId);
        return user;
    }
}
