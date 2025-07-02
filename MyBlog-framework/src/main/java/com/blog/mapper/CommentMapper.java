package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.dto.CommentDto;
import com.blog.pojo.entity.Comment;
import com.blog.pojo.vo.CommentVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

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

    @Select("select like_count from comment where id = #{commentId}")
    Long getLikeByCommentId(Long commentId);

    @Update("UPDATE comment SET like_count = #{likeCount}, is_liked = #{isLike} WHERE id = #{commentId}")
    void saveLikeByCommentId(@Param("commentId") Long commentId,
                             @Param("likeCount") Long likeCount,
                             @Param("isLike") String isLike); // '0' or '1'
    @Select("select * from comment where id = #{commentId}")
    CommentDto getCommentById(Long commentId);
    @Select("select * from comment where id = #{commentId}")
    Comment getCommentReplyById(Long toCommentId);

    @Select("select * from comment where id = #{commentId}")
    Comment getCommntById(Long commentId);

    @Update("update comment set del_flag = 0 ,update_time = now() where id = #{commentId}")
    void keepCommentByCommentId(Long commentId);
    @Update("update comment set del_flag = 1 , update_time = now() where id = #{commentId}")
    void deleteComment(Long commentId);
}