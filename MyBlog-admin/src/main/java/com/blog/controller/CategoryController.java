package com.blog.controller;

import com.blog.pojo.entity.Category;
import com.blog.pojo.vo.AdminCategoryVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public Result<PageBean> list(int pageNum, int pageSize) {
        return Result.success(categoryService.list(pageNum,pageSize));
    }
    @PostMapping()
    public Result addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return Result.success();
    }
    @GetMapping("{id}")
    public Result<Category> getCategory(@PathVariable Long id) {
        return Result.success(categoryService.getCategoryById(id));
    }
    @PutMapping
    public Result updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return Result.success();
    }
    @DeleteMapping("{id}")
    public Result deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return Result.success();
    }
}
