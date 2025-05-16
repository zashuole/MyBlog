package com.blog.controller;


import com.blog.annotation.SystemLog;
import com.blog.pojo.dto.CommentDto;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(businessName = "展示全部评论")
    public Result<PageBean> articleList(int pageNum, int pageSize, Long articleId){
        return Result.success(commentService.commentList(pageNum,pageSize,articleId));
    }
    @PostMapping
    @SystemLog(businessName = "增加评论")
    public Result addComment(@RequestBody CommentDto commentDto){
        commentService.addComment(commentDto);
        return Result.success();
    }
    @GetMapping("linkCommentList")
    @SystemLog(businessName = "获取全部友链评论")
    public Result<PageBean> linkCommentList(int pageNum, int pageSize, Long articleId){
        return Result.success(commentService.linkCommentList(pageNum,pageSize,articleId));
    }
}
