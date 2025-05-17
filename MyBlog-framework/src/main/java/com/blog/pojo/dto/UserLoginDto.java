package com.blog.pojo.dto;


import lombok.Data;

@Data
public class UserLoginDto {

    /**
     用户名
     **/
    private String userName;
    /**
     密码
     **/
    private String password;
}
