package com.blog.pojo.entity;


import com.blog.enums.NotificationType;
import lombok.Data;

@Data
public class Notification {
    private String id;                  // 通知ID
    private NotificationType type;     // 通知类型：reply-回复，like-点赞
    private String fromUserId;         // 触发通知的用户ID
    private String fromUsername;       // 触发通知的用户名
    private String fromUserAvatar;     // 触发通知用户头像
    private String targetId;           // 目标评论ID
    private String articleId;          // 文章ID
    private String articleTitle;       // 文章标题
    private String content;            // 评论/回复内容
    private Boolean isRead;            // 是否已读
    private String createTime;         // 通知时间（格式："yyyy-MM-dd HH:mm:ss"）
}
