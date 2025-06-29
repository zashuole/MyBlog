package com.blog.pojo.vo;

import com.blog.pojo.entity.Notification;
import com.blog.result.PageBean;
import lombok.Data;

import java.util.List;

@Data
public class NotificationsVo extends PageBean<Notification> {
    private int unreadCount;
}
