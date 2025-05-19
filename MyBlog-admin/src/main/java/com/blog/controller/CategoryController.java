package com.blog.controller;

import com.blog.pojo.vo.AdminCategoryVo;
import com.blog.result.Result;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public Result<List<AdminCategoryVo>> listAllCategory() {
        return Result.success(categoryService.getAdminCategoryList());
    }

}
