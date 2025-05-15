package com.blog.controller;

import com.blog.pojo.dto.RegisterUserDto;
import com.blog.pojo.dto.UpdateUserInfoDto;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.UserInfoVo;
import com.blog.result.Result;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public Result<UserInfoVo> userInfo(Long userId){
        return Result.success(userService.getById(userId));
    }
    @PutMapping("/userInfo")
    public Result updateUserInfo(@RequestBody UpdateUserInfoDto updateUserInfoDto){
        userService.updateUserInfo(updateUserInfoDto);
        return Result.success();
    }
    @PostMapping("/register")
    public Result register(@RequestBody RegisterUserDto registerUserDto){
        userService.register(registerUserDto);
        return Result.success();

    }
}
