package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联表(UserRole)实体类
 *
 * @author makejava
 * @since 2025-05-17 21:49:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    /**
    用户ID
    **/
    private Long userId;
    /**
    角色ID
    **/
    private Long roleId;

}

