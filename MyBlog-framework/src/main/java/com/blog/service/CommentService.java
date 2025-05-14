package com.blog.service;

import com.blog.result.PageBean;

public interface CommentService {
    PageBean commentList(int pageNum, int pageSize, Long articleId);
}
