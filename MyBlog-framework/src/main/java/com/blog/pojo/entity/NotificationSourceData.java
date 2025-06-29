package com.blog.pojo.entity;

import lombok.Data;

@Data
public class NotificationSourceData {
    // 文章评论 / 评论回复
    private String articleId;
    private String articleTitle;
    private String commentId;
    private String commentContent;

    // 回复专有
    private String replyId;
    private String replyContent;

    // 点赞
    private String targetId;     // 点赞目标ID（文章/评论）
    private String targetType;   // article / comment
    private String targetTitle;

    // 举报
    private String reportReason;
    private String reportContent;
}