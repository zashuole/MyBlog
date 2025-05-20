package com.blog.service.serviceImpl;

import com.blog.mapper.CategoryMapper;
import com.blog.pojo.entity.Category;
import com.blog.pojo.vo.AdminCategoryVo;
import com.blog.pojo.vo.CategoryVo;
import com.blog.result.PageBean;
import com.blog.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public List<AdminCategoryVo> getAdminCategoryList() {
        return categoryMapper.getAdminCategoryList();
    }

    @Override
    public PageBean list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Category> page = categoryMapper.list();
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryMapper.deleteById(id);
    }
}
