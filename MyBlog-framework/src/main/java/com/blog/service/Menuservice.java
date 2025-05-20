package com.blog.service;

import com.blog.pojo.entity.Menu;
import com.blog.pojo.vo.MenusVo2;
import com.blog.pojo.vo.MenusVo3;

import java.util.List;

public interface Menuservice {
    List<Menu> getMenus();

    void addMenu(Menu menu);

    void deleteMenu(Long id);

    Menu getMenusById(Long id);

    void updateMenu(Menu menu);

    List<MenusVo2> getTree();

    MenusVo3 getTreeByRoleId(Long roleId);
}
