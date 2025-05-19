package com.blog.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticalDto {
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
     标签分类
     */
    private List<Long> tags;
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
     是否允许评论 1是，0否
     **/
    private String isComment;
}
