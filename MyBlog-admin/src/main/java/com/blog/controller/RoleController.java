package com.blog.controller;


import com.blog.pojo.dto.ChangeStatusDto;
import com.blog.pojo.entity.Role;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role/")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result<PageBean> list(int pageNum, int pageSize) {
        return Result.success(roleService.list(pageNum,pageSize));
    }
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDto changeStatusDto) {
        roleService.changeStatus(changeStatusDto);
        return Result.success();
    }
}
