package com.blog.pojo.entity;

import java.util.Date;

import com.blog.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知表(CommentNotification)实体类
 *
 * @author makejava
 * @since 2025-06-29 14:07:57
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentNotification{

    private Long id;
    /**
    通知类型：
        ARTICLE_COMMENT-文章评论,
        COMMENT_REPLY-回复评论,
        COMMENT_LIKE-评论点赞,
        ARTICLE_LIKE-文章点赞,
        COMMENT_REPORT-评论举报
    **/
    private NotificationType type;
    /**
    通知标题
    **/
    private String title;
    /**
    通知内容
    **/
    private String content;
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
    回复ID（评论回复时有值）
    **/
    private Long replyId;
    /**
    举报原因（举报类型时有值）
    **/
    private String reportReason;
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
    /**
    删除标志（0代表未删除，1代表已删除）
    **/
    private Integer delFlag;

}

