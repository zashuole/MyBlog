package com.blog.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论通知表(CommentNotification)实体类
 *
 * @author makejava
 * @since 2025-06-29 12:21:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentNotification{

    private Long id;
    /**
    通知类型：reply-回复，like-点赞
    **/
    private String type;
    /**
    触发通知的用户ID
    **/
    private Long fromUserId;
    /**
    接收通知的用户ID
    **/
    private Long toUserId;
    /**
    关联的文章ID
    **/
    private Long articleId;
    /**
    关联的评论ID
    **/
    private Long commentId;
    /**
    被回复的评论ID（回复类型时有值）
    **/
    private Long parentCommentId;
    /**
    是否已读
    **/
    private Integer isRead;
    /**
    创建时间
    **/
    private Date createTime;
    /**
    更新时间
    **/
    private Date updateTime;

}

