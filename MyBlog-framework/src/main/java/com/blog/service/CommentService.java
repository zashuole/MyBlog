package com.blog.service;

import com.blog.pojo.dto.CommentDto;
import com.blog.result.PageBean;
import com.blog.pojo.dto.CommentLikeDto;

public interface CommentService {
    PageBean commentList(int pageNum, int pageSize, Long articleId);

    void addComment(CommentDto commentDto);

    PageBean linkCommentList(int pageNum, int pageSize, Long articleId);

    Long like(Long commentId);

    CommentLikeDto getCommentById(Long commentId);

    Long unlike(Long commentId);
}
