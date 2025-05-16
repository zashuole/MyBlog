package com.blog.controller;


import com.blog.annotation.SystemLog;
import com.blog.pojo.vo.CategoryVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //查询分类
    @GetMapping("getCategoryList")
    @SystemLog(businessName = "查询分类")
    public Result<List<CategoryVo>> getCategoryList() {
        return Result.success(categoryService.getCategoryList());
    }
}
