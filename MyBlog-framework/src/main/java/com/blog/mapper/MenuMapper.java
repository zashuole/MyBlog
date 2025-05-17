package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("select perms from sys_menu where (menu_type = 'C' or menu_type = 'F') and status = 0 and del_flag = 0")
    List<String> getAllPermsWhoAdmin();
    @Select("select perms from sys_menu where id = #{menuId} and status = 0 and del_flag = 0")
    String getPermBymenuId(Integer menuId);
}
