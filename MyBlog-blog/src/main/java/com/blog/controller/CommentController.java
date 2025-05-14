package com.blog.controller;

import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public Result<PageBean> articleList(int pageNum, int pageSize, Long articleId){
        return Result.success(commentService.commentList(pageNum,pageSize,articleId));
    }
}
