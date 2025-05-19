package com.blog.service.serviceImpl;

import com.blog.mapper.RoleMapper;
import com.blog.pojo.dto.ChangeStatusDto;
import com.blog.pojo.entity.Role;
import com.blog.result.PageBean;
import com.blog.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
}
