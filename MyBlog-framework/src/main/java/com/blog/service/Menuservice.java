package com.blog.service;

import com.blog.pojo.entity.Menu;
import com.blog.pojo.vo.MenusVO2;

import java.util.List;

public interface Menuservice {
    List<Menu> getMenus();

    void addMenu(Menu menu);

    void deleteMenu(Long id);

    Menu getMenusById(Long id);

    void updateMenu(Menu menu);

    List<MenusVO2> getTree();
}
