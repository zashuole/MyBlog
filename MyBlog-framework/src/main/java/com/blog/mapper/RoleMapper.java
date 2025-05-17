package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT r.role_key FROM sys_user_role ur JOIN sys_role r ON ur.role_id = r.id WHERE ur.user_id = #{userId}\n")
    String getRolesByUserId(Long userId);

    @Select("SELECT DISTINCT rm.menu_id FROM sys_user_role ur JOIN sys_role_menu rm ON ur.role_id = rm.role_id WHERE ur.user_id = #{userId}")
    List<Integer> getMenusIdByUserId(Long userId);
}
