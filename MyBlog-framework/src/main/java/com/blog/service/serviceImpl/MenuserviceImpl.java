package com.blog.service.serviceImpl;

import com.blog.mapper.MenuMapper;
import com.blog.mapper.RoleMapper;
import com.blog.pojo.entity.Menu;
import com.blog.pojo.vo.MenusVO2;
import com.blog.service.Menuservice;
import com.blog.utils.SecurityUtils;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuserviceImpl implements Menuservice {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Menu> getMenus() {
        //获取当前的用户id
        Long userId = SecurityUtils.getUserId();
        //查询roleMenu表，看他的权限
        List<Long> menuIds = roleMapper.getMenusIdByUserId(userId);
        //记录结果
        List<Menu> menus = new ArrayList<>();
        //如果menuIds只有一条，即为超级管理员
        if(menuIds.size() == 1&&menuIds.get(0).intValue() == 0){
            //获取全部权限，状态为正常，未被删除的
            menus = menuMapper.getAllMenusWhoAdmin();
        }else{
            for (Long menuId : menuIds) {
                //根据id查询并存入permissions
                Menu menu = menuMapper.getMenuById(menuId);
                if(menu != null){
                    menus.add(menu);
                }
            }
        }
        return menus;
    }

    @Override
    public void addMenu(Menu menu) {
        menu.setDelFlag("0");
        menuMapper.addMenu(menu);
    }

    @Override
    public void deleteMenu(Long id) {
        menuMapper.deleteById(id);
    }

    @Override
    public Menu getMenusById(Long id) {
        return menuMapper.getMenuById(id);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }

    @Override
    public List<MenusVO2> getTree() {
        //获取当前的用户id
        Long userId = SecurityUtils.getUserId();
        //查询roleMenu表，看他的权限
        List<Long> menuIds = roleMapper.getMenusIdByUserId(userId);
        //记录结果
        List<Menu> menus = new ArrayList<>();
        //如果menuIds只有一条，即为超级管理员
        if(menuIds.size() == 1&&menuIds.get(0).intValue() == 0){
            //获取全部权限，状态为正常，未被删除的
            menus = menuMapper.getAllMenusWhoAdmin();
        }else{
            for (Long menuId : menuIds) {
                //根据id查询并存入permissions
                Menu menu = menuMapper.getMenuById(menuId);
                if(menu != null){
                    menus.add(menu);
                }
            }
        }
        //menus是该用户所获得的菜单
        // 转换成 VO 对象
        List<MenusVO2> voList = menus.stream().map(menu -> {
            MenusVO2 vo = new MenusVO2();
            vo.setId(menu.getId());
            vo.setLabel(menu.getMenuName());
            vo.setParentId(menu.getParentId());
            return vo;
        }).collect(Collectors.toList());
        //现在把menus菜单组织成树形结构
        // 构建树结构
        return buildTree(voList, 0L);
    }
    private List<MenusVO2> buildTree(List<MenusVO2> nodes, Long parentId) {
        List<MenusVO2> tree = new ArrayList<>();
        for (MenusVO2 node : nodes) {
            if (parentId.equals(node.getParentId())) {
                List<MenusVO2> children = buildTree(nodes, node.getId());
                node.setChildren(children);
                tree.add(node);
            }
        }
        return tree;
    }
}
