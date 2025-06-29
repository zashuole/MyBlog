package com.blog.controller;

import com.blog.annotation.SystemLog;
import com.blog.pojo.dto.ArticleCommentDto;
import com.blog.pojo.entity.Article;
import com.blog.pojo.entity.ArticleLike;
import com.blog.pojo.entity.Comment;
import com.blog.pojo.vo.ArticleLikeVo;
import com.blog.pojo.vo.HotArticleVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.ArticleLikeService;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticalController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleLikeService articleLikeService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/hotArticle")
    @SystemLog(businessName = "获取全部热点文章列表")
    public Result<List<HotArticleVo>> hotArticleList(@RequestParam(required = false)  Long categoryId){
        return Result.success(articleService.hotArticleList(categoryId));
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
    @PostMapping("/sendComment")
    public Result sendComment(@RequestBody ArticleCommentDto articleCommentDto){
        commentService.sendComment(articleCommentDto);
        return Result.success();
    }

    @GetMapping("search")
    public Result<PageBean> search(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(required = false) String keyword,
                                        @RequestParam(required = false) Long categoryId){
        return Result.success(articleService.search(pageNum,pageSize,keyword,categoryId));
    }
    @GetMapping("/like/status/{articleId}")
    public Result<ArticleLikeVo> likeStatus(@PathVariable Long articleId){
        return Result.success(articleLikeService.likeStatus(articleId));
    }
    @PostMapping("/like/{articleId}")
    public Result<ArticleLikeVo> likeArticle(@PathVariable Long articleId){
        return Result.success(articleLikeService.likeArticle(articleId));
    }

    @PostMapping("/unlike/{articleId}")
    public Result<ArticleLikeVo> unlikeArticle(@PathVariable Long articleId){
        return Result.success(articleLikeService.unlikeArticle(articleId));
    }
}
