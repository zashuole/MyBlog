package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("select perms from sys_menu where (menu_type = 'C' or menu_type = 'F') and status = 0 and del_flag = 0")
    List<String> getAllPermsWhoAdmin();

    @Select("select perms from sys_menu where (menu_type = 'C' or menu_type = 'F') and id = #{menuId} and status = 0 and del_flag = 0")
    String getPermBymenuId(Long menuId);

    @Select("select * from sys_menu where (menu_type = 'C' or menu_type = 'M') and status = 0 and del_flag = 0")
    List<Menu> getAllMenusWhoAdmin();

    @Select("select * from sys_menu where (menu_type = 'C' or menu_type = 'M') and id = #{menuId} and status = 0 and del_flag = 0")
    Menu getMenuBymenuId(Long menuId);

    @Select("select * from sys_menu where status = 0")
    List<Menu> getMenusByMenuNameAndStatus();

    @AutoFill(OperationType.INSERT)
    void addMenu(Menu menu);

    @Delete("delete from sys_menu where id = #{id}")
    void deleteById(Long id);
    @Select("select * from sys_menu where id = #{id}")
    Menu getMenuById(Long id);

    @AutoFill(OperationType.UPDATE)
    void updateMenu(Menu menu);

    @Select("select * from sys_menu where status = 0")
    List<Menu> getMenus();
}
