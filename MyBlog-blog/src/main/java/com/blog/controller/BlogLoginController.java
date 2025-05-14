package com.blog.controller;

import com.blog.pojo.entity.User;
import com.blog.result.Result;
import com.blog.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){

        return blogLoginService.login(user);
    }
}
