package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.CommentNotification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentNotificationMapper {
    void save(CommentNotification commentNotification);

    @Update("""
        UPDATE comment_notification
        SET del_flag = 1,
            update_time = NOW()
        WHERE article_id = #{articleId}
          AND from_user_id = #{userId}
          AND del_flag = 0
        """)
    void setDelTo1ByArticleAndFromUser(@Param("articleId") Long articleId,
                                       @Param("userId") Long userId);
    @Select("SELECT * FROM comment_notification WHERE article_id = #{articleId} AND from_user_id = #{userId}")
    CommentNotification getByByArticleAndFromUser(@Param("articleId") Long articleId,
                                                  @Param("userId") Long userId);

    @Update("""
        UPDATE comment_notification
        SET del_flag = 0,
            update_time = NOW()
        WHERE article_id = #{articleId}
          AND from_user_id = #{userId}
          AND del_flag = 1
        """)
    void setDelTo0ByArticleAndFromUser(@Param("articleId") Long articleId,
                                       @Param("userId") Long userId);
}
