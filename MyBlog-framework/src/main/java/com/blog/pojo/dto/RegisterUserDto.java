package com.blog.pojo.dto;

import lombok.Data;

@Data
public class RegisterUserDto {

    /**
     用户名
     **/
    private String userName;
    /**
     昵称
     **/
    private String nickName;
    /**
     密码
     **/
    private String password;
    /**
     邮箱
     **/
    private String email;
}
