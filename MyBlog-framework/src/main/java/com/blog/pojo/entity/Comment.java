package com.blog.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论表(Comment)实体类
 *
 * @author makejava
 * @since 2025-05-14 16:44:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{

    private Long id;
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

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    /**
     删除标志（0代表未删除，1代表已删除）
     **/
    private Integer delFlag;
    /**
     点赞数
     **/
    private Long likeCount;
    /**
     当前用户是否点赞（0否，1是）
     **/
    private String isLiked;
    /**
     举报数
     **/
    private Long reportCount;
    /**
     评论状态（0待审核，1正常，2已拒绝，3已删除）
     **/
    private String status;
    /**
     是否热门评论（0否，1是）
     **/
    private String isHot;
    /**
     评论评分（用于排序）
     **/
    private Double score;

}

