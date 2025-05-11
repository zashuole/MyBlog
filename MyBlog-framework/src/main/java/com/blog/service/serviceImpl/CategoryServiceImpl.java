package com.blog.service.serviceImpl;

import com.blog.mapper.CategoryMapper;
import com.blog.pojo.vo.CategoryVo;
import com.blog.service.CategoryService;
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
}
