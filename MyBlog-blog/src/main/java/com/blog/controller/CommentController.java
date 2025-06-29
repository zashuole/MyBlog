package com.blog.controller;


import com.blog.annotation.SystemLog;
import com.blog.pojo.dto.CommentDto;
import com.blog.pojo.dto.CommentLikeDto;
import com.blog.pojo.dto.CommentReportDto;
import com.blog.pojo.vo.NotificationsVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @PostMapping("/like")
    public Result<Long> like(@RequestBody Map<String, Object> params){
        Long commentId = Long.valueOf(String.valueOf(params.get("commentId").toString()));
        return Result.success(commentService.like(commentId));
    }
    @PostMapping("/unlike")
    public Result<Long> unlike(@RequestBody Map<String, Object> params){
        Long commentId = Long.valueOf(String.valueOf(params.get("commentId").toString()));
        return Result.success(commentService.unlike(commentId));
    }
    @GetMapping("/like/status/{commentId}")
    public Result<CommentLikeDto> commentStatus(@PathVariable Long commentId){
        return Result.success(commentService.getCommentById(commentId));
    }
    @PostMapping("/report")
    @SystemLog(businessName = "举报评论")
    public Result reportComment(@RequestBody CommentReportDto reportDto) {
        return commentService.reportComment(reportDto);
    }
//    @GetMapping("/notifications")
//    public Result<NotificationsVo> notifications(int pageNum, int pageSize,String type,Boolean isRead) {
//
//    }
}
