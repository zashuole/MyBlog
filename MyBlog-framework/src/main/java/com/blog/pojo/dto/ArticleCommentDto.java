package com.blog.pojo.dto;

import lombok.Data;

@Data
public class ArticleCommentDto {
    /**
     评论类型（0代表文章评论，1代表友链评论）
     **/
    private String type;
    /**
     文章id
     **/
    private Long articleId;
    /**
     根评论id
     **/
    private Long rootId;
    /**
     评论内容
     **/
    private String content;
    /**
     所回复的目标评论的userid
     **/
    private Long toCommentUserId;
    /**
     回复目标评论id
     **/
    private Long toCommentId;
}
