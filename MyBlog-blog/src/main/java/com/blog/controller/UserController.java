package com.blog.controller;

import com.blog.pojo.entity.User;
import com.blog.result.Result;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public Result<User> userInfo(Long userId){
        return Result.success(userService.getById(userId));
    }
}
