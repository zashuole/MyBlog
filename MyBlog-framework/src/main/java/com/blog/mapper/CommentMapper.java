package com.blog.mapper;

import com.blog.pojo.entity.Comment;
import com.blog.pojo.vo.CommentVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment where article_id = #{articleId} and root_id = -1")
    Page<Comment> getCommentListByArticleId(Long articleId);

    @Select("select * from comment where to_comment_id = #{id}")
    List<Comment> getChildenCommentsById(Long id);
}
