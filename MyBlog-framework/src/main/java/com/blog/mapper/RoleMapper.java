package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.dto.ChangeStatusDto;
import com.blog.pojo.dto.RoleDto;
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

    @Select("select * from sys_role where del_flag = 0")
    Page<Role> list();

    @AutoFill(OperationType.UPDATE)
    @Update("update sys_role set status = #{status},update_by = #{updateBy},update_time = #{updateTime} where id = #{roleId}")
    void changeStatus(ChangeStatusDto changeStatusDto);

    @AutoFill(OperationType.INSERT)
    Long addRole(Role role);

    @Insert("INSERT INTO sys_role_menu(role_id, menu_id) VALUES (#{roleId}, #{menuId})")
    void addRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    @Select("select * from sys_role where id = #{id}")
    RoleDto getRoleById(Integer id);

    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Long> getMenusIdByRoleId(Long roleId);


    @Update("update sys_role set del_flag = 1 where id = #{id}")
    void deleteById(Long id);

    @Select("select * from sys_role where del_flag = 0")
    List<Role> getAllRole();

    @Insert("insert into sys_user_role (user_id, role_id) VALUES (#{userId},#{roleId})")
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Delete("delete from sys_user_role where user_id = #{userId}")
    void deleteUserRoleById(Long userId);
}
