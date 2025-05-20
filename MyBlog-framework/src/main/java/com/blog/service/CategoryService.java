package com.blog.service;

import com.blog.pojo.entity.Category;
import com.blog.pojo.vo.AdminCategoryVo;
import com.blog.pojo.vo.CategoryVo;
import com.blog.result.PageBean;

import java.util.List;

public interface CategoryService {
    List<CategoryVo> getCategoryList();

    List<AdminCategoryVo> getAdminCategoryList();

    PageBean list(int pageNum, int pageSize);

    void addCategory(Category category);

    Category getCategoryById(Long id);

    void updateCategory(Category category);

    void deleteById(Long id);
}
