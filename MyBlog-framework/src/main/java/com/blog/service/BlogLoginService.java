package com.blog.service;

import com.blog.pojo.entity.User;
import com.blog.result.Result;

public interface BlogLoginService {
    Result login(User user);
}
