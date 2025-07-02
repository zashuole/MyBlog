package com.blog.mapper;

import com.blog.enums.NotificationType;
import com.blog.pojo.entity.CommentNotification;
import com.blog.pojo.entity.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Select("select count(*) from comment_notification where to_user_id = #{userId} and del_flag = 0 and type != 'COMMENT_REPORT' and is_read = 0")
    Integer getAllMessageByUserId(Long userId);
    @Select("select count(*) from comment_notification where to_user_id = #{userId} and type = #{type} and del_flag = 0 and is_read = 0")
    Integer getMessageNumberByUserIdAndType(@Param("userId") Long userId,
                                            @Param("type") NotificationType type);

    @Select("select count(*) from comment_notification where type = #{type} and del_flag = 0 and is_read = 0")
    Integer getReportMessageNumberByUserIdAndType(NotificationType type);

    @Select("select * from comment_notification where to_user_id = #{userId} and del_flag = 0")
    List<CommentNotification> getListByUserId(Long userId);
    @Select("select * from comment_notification where to_user_id = #{userId} and type = #{type} and del_flag = 0 and is_read = 0")
    List<CommentNotification> getListByUserIdAndType(@Param("userId") Long userId,
                                                     @Param("type") String type);

    @Update("update comment_notification set is_read = 1 ,update_time = NOW() where id = #{notificationId}")
    void isReadByNotificationId(Long notificationId);

    @Update("update comment_notification set is_read = 1 ,update_time = NOW() where to_user_id = #{userId}")
    void setAllIsRead(Long userId);

    @Update("update comment_notification set del_flag = 1 ,update_time = NOW() where id = #{notificationId}")
    void delete(Long notificationId);
    @Update("update comment_notification set del_flag = 1 ,update_time = NOW() where to_user_id = #{userId}")
    void clear(Long userId);
}
