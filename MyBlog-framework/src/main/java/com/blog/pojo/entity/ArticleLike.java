package com.blog.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章点赞表(ArticleLike)实体类
 *
 * @author makejava
 * @since 2025-06-29 12:53:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLike{
    /**
    主键ID
    **/
    private Long id;
    /**
    文章ID
    **/
    private Long articleId;
    /**
    用户ID
    **/
    private Long userId;
    /**
    点赞时间
    **/
    private Date createTime;

}

