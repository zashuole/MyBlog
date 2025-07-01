package com.blog.pojo.vo;


import lombok.Data;

@Data
public class NotificationQueryRequest {
    private Integer pageNum;
    private Integer pageSize;
    private String type; // all, article_comment, comment_reply, comment_like, article_like, comment_report
}