package com.blog.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo {

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
     回复对象名称
     **/
    private String toCommentUserName;
    /**
     所回复的目标评论的userid
     **/
    private Long toCommentUserId;
    /**
     回复目标评论id
     **/
    private Long toCommentId;
    /**
     头像
     **/
    private String avatar;

    private Date createTime;
    /**
     评论人姓名
     **/
    private String username;
    /**
     存储子评论
     **/
    private List<CommentVo> children;
}
