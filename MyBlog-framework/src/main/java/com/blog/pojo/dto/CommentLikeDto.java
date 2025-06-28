package com.blog.pojo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class CommentLikeDto {
    /**
     是否点赞
     **/
    private boolean isLiked;
    /**
     点赞数
     **/
    private Long likeCount;

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }


}
