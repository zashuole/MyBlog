package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.CommentReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentReportMapper {

    void save(CommentReport commentReport);

    @Select("SELECT COUNT(1) > 0 FROM comment_report WHERE user_id = #{userId} AND comment_id = #{commentId} AND del_flag = 0")
    boolean existsByUserIdAndCommentId(@Param("userId") Long userId,
                                       @Param("commentId") Long commentId);

    @Select("select * from comment_report where comment_id = #{commentId}")
    CommentReport getReportByCommentId(Long commentId);
}
