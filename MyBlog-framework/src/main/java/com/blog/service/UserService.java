package com.blog.service;

import com.blog.pojo.dto.RegisterUserDto;
import com.blog.pojo.dto.UpdateUserInfoDto;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.UserInfoVo;

public interface UserService {
    UserInfoVo getById(Long userId);

    void updateUserInfo(UpdateUserInfoDto updateUserInfoDto);

    void register(RegisterUserDto registerUserDto);
}
