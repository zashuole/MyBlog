package com.blog.service;


import com.blog.pojo.vo.ArticleLikeVo;

public interface ArticleLikeService {
    ArticleLikeVo likeStatus(Long articleId);

    ArticleLikeVo likeArticle(Long articleId);

    ArticleLikeVo unlikeArticle(Long articleId);
}
