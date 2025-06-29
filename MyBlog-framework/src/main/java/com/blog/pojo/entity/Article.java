package com.blog.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章表(Article)实体类
 *
 * @author makejava
 * @since 2025-05-10 17:03:24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article{

    private Long id;
    /**
    标题
    **/
    private String title;
    /**
    文章内容
    **/
    private String content;
    /**
    文章摘要
    **/
    private String summary;
    /**
    所属分类id
    **/
    private Long categoryId;
    /**
    缩略图
    **/
    private String thumbnail;
    /**
    是否置顶（0否，1是）
    **/
    private String isTop;
    /**
    状态（0已发布，1草稿）
    **/
    private String status;
    /**
    访问量
    **/
    private Long viewCount;
    /**
    是否允许评论 1是，0否
    **/
    private String isComment;

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
}

