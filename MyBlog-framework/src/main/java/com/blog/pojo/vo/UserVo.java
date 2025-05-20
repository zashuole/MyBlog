package com.blog.pojo.vo;

import com.blog.pojo.entity.Role;
import com.blog.pojo.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    List<Long> roleIds;

    List<Role> roles;

    User user;
}
