package com.blog.mapper;

import com.blog.pojo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("select perms from sys_menu where (menu_type = 'C' or menu_type = 'F') and status = 0 and del_flag = 0")
    List<String> getAllPermsWhoAdmin();

    @Select("select perms from sys_menu where (menu_type = 'C' or menu_type = 'F') and id = #{menuId} and status = 0 and del_flag = 0")
    String getPermBymenuId(Integer menuId);

    @Select("select * from sys_menu where (menu_type = 'C' or menu_type = 'M') and status = 0 and del_flag = 0")
    List<Menu> getAllMenusWhoAdmin();

    @Select("select * from sys_menu where (menu_type = 'C' or menu_type = 'M') and id = #{menuId} and status = 0 and del_flag = 0")
    Menu getMenuBymenuId(Integer menuId);
}
