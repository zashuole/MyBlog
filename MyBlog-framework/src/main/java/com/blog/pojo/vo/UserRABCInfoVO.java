package com.blog.pojo.vo;


import lombok.Data;

import java.util.List;

@Data
public class UserRABCInfoVO {
    /*
    权限标识
     */
    private List<String> permissions;
    /*
    角色
     */
    private String roles;
    /*
    用户信息
     */
    private UserInfoVo user;
}
