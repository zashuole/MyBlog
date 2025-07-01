package com.blog.mapper;

import com.blog.enums.NotificationType;
import com.blog.pojo.entity.CommentNotification;
import com.blog.pojo.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Select("select count(*) from comment_notification where to_user_id = #{userId} and del_flag = 0 and type != 'COMMENT_REPORT'")
    Integer getAllMessageByUserId(Long userId);
    @Select("select count(*) from comment_notification where to_user_id = #{userId} and type = #{type} and del_flag = 0")
    Integer getMessageNumberByUserIdAndType(@Param("userId") Long userId,
                                            @Param("type") NotificationType type);

    @Select("select count(*) from comment_notification where type = #{type} and del_flag = 0")
    Integer getReportMessageNumberByUserIdAndType(NotificationType type);

    @Select("select * from comment_notification where to_user_id = #{userId} and del_flag = 0")
    List<CommentNotification> getListByUserId(Long userId);
    @Select("select * from comment_notification where to_user_id = #{userId} and type = #{type} and del_flag = 0")
    List<CommentNotification> getListByUserIdAndType(@Param("userId") Long userId,
                                                     @Param("type") String type);
}
