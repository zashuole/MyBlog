package com.blog.service;

import com.blog.pojo.vo.NotificationQueryRequest;
import com.blog.pojo.vo.UnreadNotificationCountVo;
import com.blog.result.NotificationPageData;

public interface NotificationService {
    UnreadNotificationCountVo getUnreadCount();

    NotificationPageData list(NotificationQueryRequest notificationQueryRequest);

    void read(Long notificationId);

    void batch();

    void delete(Long notificationId);

    void clear();
}
