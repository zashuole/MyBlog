package com.blog.service;

import com.blog.pojo.dto.RegisterUserDto;
import com.blog.pojo.dto.UpdateUserInfoDto;
import com.blog.pojo.dto.UserDto;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.UserInfoVo;
import com.blog.pojo.vo.UserVo;
import com.blog.result.PageBean;

public interface UserService {
    UserInfoVo getById(Long userId);

    void updateUserInfo(UpdateUserInfoDto updateUserInfoDto);

    void register(RegisterUserDto registerUserDto);

    PageBean list(int pageNum, int pageSize);

    void save(UserDto userDto);

    void delete(Long userId);

    UserVo getUserDetailById(Long userId);

    void updateUser(UserDto userDto);
}
