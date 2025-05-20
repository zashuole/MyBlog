package com.blog.controller;


import com.blog.pojo.dto.UserDto;
import com.blog.pojo.entity.Role;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.UserVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.RoleService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/list")
    public Result<PageBean> list(int pageNum, int pageSize){
        return Result.success(userService.list(pageNum,pageSize));
    }
    @PostMapping()
    public Result save(@RequestBody UserDto userDto){
        userService.save(userDto);
        return Result.success();
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id){
        userService.delete(id);
        return Result.success();
    }
    @GetMapping("{id}")
    public Result<UserVo> get(@PathVariable Long id){
        return Result.success(userService.getUserDetailById(id));
    }
    @PutMapping()
    public Result update(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
        return Result.success();
    }
}
