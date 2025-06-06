package com.blog.pojo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;
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
     账号状态（0正常 1停用）
     **/
    private String status;
    /**
     邮箱
     **/
    private String email;
    /**
     手机号
     **/
    private String phonenumber;
    /**
     用户性别（0男，1女，2未知）
     **/
    private String sex;

    //角色权限
    List<Long> roleIds;
}
