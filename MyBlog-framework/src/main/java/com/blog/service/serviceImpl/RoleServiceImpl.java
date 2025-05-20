package com.blog.service.serviceImpl;

import com.blog.mapper.RoleMapper;
import com.blog.pojo.dto.ChangeStatusDto;
import com.blog.pojo.dto.RoleDto;
import com.blog.pojo.entity.Role;
import com.blog.result.PageBean;
import com.blog.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageBean list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Role> page = roleMapper.list();
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public void changeStatus(ChangeStatusDto changeStatusDto) {
        roleMapper.changeStatus(changeStatusDto);
    }

    @Override
    public void addRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        //主键返回
        roleMapper.addRole(role);
        List<Long> menuIds = roleDto.getMenuIds();
        for (Long menuId : menuIds) {
            roleMapper.addRoleMenu(role.getId(),menuId);
        }
    }

    @Override
    public RoleDto getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public void deleteById(Long id) {
        roleMapper.deleteById(id);
    }

    @Override
    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }
}
