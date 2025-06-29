package com.blog.pojo.vo;

import lombok.Data;

@Data
public class ArticleLikeVo {
    //当前文章是否点赞
    private Boolean isLiked;
    //文章总点赞数
    private Long likeCount;
}
