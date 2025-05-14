package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.Comment;
import com.blog.pojo.vo.CommentVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment where article_id = #{articleId} and root_id = -1 and type = 0")
    Page<Comment> getCommentListByArticleId(Long articleId);

    @Select("select * from comment where to_comment_id = #{id} and type = 0")
    List<Comment> getChildenCommentsById(Long id);

    @AutoFill(value = OperationType.INSERT)
    void addComment(Comment comment);

    @Select("select create_by from comment where id = #{toCommentId}")
    Long getUserIdByToCommentId(Long toCommentId);
    @Select("select * from comment where article_id = #{articleId} and root_id = -1 and type = 1")
    Page<Comment> getLinkCommentListByArticleId(Long articleId);
    @Select("select * from comment where to_comment_id = #{id} and type = 1")
    List<Comment> getChildenLinkCommentsById(Long parentId);
}
