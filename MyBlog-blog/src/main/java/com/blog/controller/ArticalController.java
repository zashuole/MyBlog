package com.blog.controller;

import com.blog.annotation.SystemLog;
import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.HotArticleVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticalController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    @SystemLog(businessName = "获取全部热点文章列表")
    public Result<List<HotArticleVo>> hotArticleList(){
        return Result.success(articleService.hotArticleList());
    }
    //分页查询文章
    @GetMapping("/articleList")
    @SystemLog(businessName = "分页查询文章")
    public Result<PageBean> articleList(int pageNum, int pageSize, Long categoryId){
        return Result.success(articleService.articleList(pageNum,pageSize,categoryId));
    }
    //查看文章详情
    @GetMapping("/{id}")
    @SystemLog(businessName = "查看文章详情")
    public Result<Article> getArticleDetail(@PathVariable Long id){
        return Result.success(articleService.getArticleDetail(id));
    }
    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "更新文章浏览量")
    public Result updateViewCount(@PathVariable Long id){
        articleService.updateViewCount(id);
        return Result.success();
    }
}
