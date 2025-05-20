package com.blog.controller;

import com.blog.pojo.entity.Menu;
import com.blog.pojo.vo.MenusVo2;
import com.blog.pojo.vo.MenusVo3;
import com.blog.result.Result;
import com.blog.service.Menuservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private Menuservice menuservice;

    @GetMapping("/list")
    public Result<List<Menu>> getMenus() {
        return Result.success(menuservice.getMenus());
    }
    @PostMapping()
    public Result addMenu(@RequestBody Menu menu) {
        menuservice.addMenu(menu);
        return Result.success();
    }
    @DeleteMapping("{id}")
    public Result deleteMenu(@PathVariable Long id) {
        menuservice.deleteMenu(id);
        return Result.success();
    }
    @GetMapping("{id}")
    public Result<Menu> getMenu(@PathVariable Long id) {

        return Result.success(menuservice.getMenusById(id));
    }
    @PutMapping()
    public Result updateMenu(@RequestBody Menu menu) {
        menuservice.updateMenu(menu);
        return Result.success();
    }
    @GetMapping("/treeselect")
    public Result<List<MenusVo2>> getMenuTree() {
        return Result.success(menuservice.getTree());
    }
    @GetMapping("/roleMenuTreeselect/{id}")
    public Result<MenusVo3> getMenuTree(@PathVariable Long id) {
        return Result.success(menuservice.getTreeByRoleId(id));
    }
}
