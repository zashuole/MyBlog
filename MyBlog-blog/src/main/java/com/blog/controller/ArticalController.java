package com.blog.controller;

import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.HotArticleVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticalController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public Result<List<HotArticleVo>> hotArticleList(){
        return Result.success(articleService.hotArticleList());
    }
    //分页查询文章
    @GetMapping("/articleList")
    public Result<PageBean> articleList(int pageNum, int pageSize, Long categoryId){
        return Result.success(articleService.articleList(pageNum,pageSize,categoryId));
    }
    //查看文章详情
    @GetMapping("/{id}")
    public Result<Article> getArticleDetail(@PathVariable Long id){
        return Result.success(articleService.getArticleDetail(id));
    }
}
