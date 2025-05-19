package com.blog.service;

import com.blog.pojo.vo.AdminCategoryVo;
import com.blog.pojo.vo.CategoryVo;

import java.util.List;

public interface CategoryService {
    List<CategoryVo> getCategoryList();

    List<AdminCategoryVo> getAdminCategoryList();
}
