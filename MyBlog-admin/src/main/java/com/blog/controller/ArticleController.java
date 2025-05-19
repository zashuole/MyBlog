package com.blog.controller;

import com.blog.pojo.dto.ArticleDto;
import com.blog.pojo.vo.ArticleVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping()
    public Result addArticle(@RequestBody ArticleDto articleDto){
        articleService.addArtical(articleDto);
        return Result.success();
    }
    @GetMapping("list")
    public Result<PageBean> list(int pageNum,int pageSize){
        return Result.success(articleService.list(pageNum,pageSize));
    }
    @GetMapping("{id}")
    public Result<ArticleVo> getArticle(@PathVariable Long id){
        return Result.success(articleService.getAdminArticleById(id));
    }
    @PutMapping()
    public Result updateArticle(@RequestBody ArticleDto articleDto){
        articleService.updateArtical(articleDto);
        return Result.success();
    }
    @DeleteMapping("{id}")
    public Result deleteArticle(@PathVariable Long id){
        articleService.deleteArticleById(id);
        return Result.success();
    }
}
