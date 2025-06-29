package com.blog.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论举报表(CommentReport)实体类
 *
 * @author makejava
 * @since 2025-06-28 21:15:41
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReport {

    private Long id;
    /**
    评论id
    **/
    private Long commentId;
    /**
    举报用户id
    **/
    private Long userId;
    /**
    举报原因
    **/
    private String reason;
    /**
    举报描述
    **/
    private String description;
    /**
    处理状态（0待处理，1已处理，2已驳回）
    **/
    private String status;
    /**
    举报时间
    **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    处理时间
    **/
    private Date handleTime;
    /**
    处理人id
    **/
    private Long handleBy;
    /**
    处理结果
    **/
    private String handleResult;
    /**
    删除标志（0代表未删除，1代表已删除）
    **/
    private Integer delFlag;

}

