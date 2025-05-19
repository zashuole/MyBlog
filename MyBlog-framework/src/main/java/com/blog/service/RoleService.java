package com.blog.service;

import com.blog.pojo.dto.ChangeStatusDto;
import com.blog.pojo.entity.Role;
import com.blog.result.PageBean;

import java.util.List;

public interface RoleService {
    PageBean list(int pageNum, int pageSize);

    void changeStatus(ChangeStatusDto changeStatusDto);
}
