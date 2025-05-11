package com.blog.service.serviceImpl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.UserMapper;
import com.blog.pojo.entity.LoginUser;
import com.blog.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userMapper.getUserbyName(name);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        return new LoginUser(user);
    }
}
