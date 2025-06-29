package com.blog.pojo.vo;

import lombok.Data;

@Data
public class UnreadNotificationCountVo {
    private Integer total;           // 总未读数
    private Integer articleComment;  // 文章评论未读数
    private Integer commentReply;    // 回复评论未读数
    private Integer commentLike;     // 评论点赞未读数
    private Integer articleLike;     // 文章点赞未读数
    private Integer commentReport;   // 评论举报未读数
}