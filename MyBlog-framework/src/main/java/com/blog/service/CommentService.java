package com.blog.service;

import com.blog.pojo.dto.ArticleCommentDto;
import com.blog.pojo.dto.CommentDto;
import com.blog.pojo.dto.CommentReportDto;
import com.blog.result.PageBean;
import com.blog.pojo.dto.CommentLikeDto;
import com.blog.result.Result;

public interface CommentService {
    PageBean commentList(int pageNum, int pageSize, Long articleId);

    void addComment(CommentDto commentDto);

    PageBean linkCommentList(int pageNum, int pageSize, Long articleId);

    Long like(Long commentId);

    CommentLikeDto getCommentById(Long commentId);

    Long unlike(Long commentId);

    Result reportComment(CommentReportDto reportDto);

    void sendComment(ArticleCommentDto articleCommentDto);
}
