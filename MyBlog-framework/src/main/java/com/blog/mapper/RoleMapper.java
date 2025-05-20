package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.dto.ChangeStatusDto;
import com.blog.pojo.entity.Role;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT r.role_key FROM sys_user_role ur JOIN sys_role r ON ur.role_id = r.id WHERE ur.user_id = #{userId}\n")
    String getRolesByUserId(Long userId);

    @Select("SELECT DISTINCT rm.menu_id FROM sys_user_role ur JOIN sys_role_menu rm ON ur.role_id = rm.role_id WHERE ur.user_id = #{userId}")
    List<Long> getMenusIdByUserId(Long userId);

    @Select("select * from sys_role")
    Page<Role> list();

    @AutoFill(OperationType.UPDATE)
    @Update("update sys_role set status = #{status},update_by = #{updateBy},update_time = #{updateTime} where id = #{roleId}")
    void changeStatus(ChangeStatusDto changeStatusDto);

    @AutoFill(OperationType.INSERT)
    Long addRole(Role role);

    @Insert("INSERT INTO sys_role_menu(role_id, menu_id) VALUES (#{roleId}, #{menuId})")
    void addRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}
