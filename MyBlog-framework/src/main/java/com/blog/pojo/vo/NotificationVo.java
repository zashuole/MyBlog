package com.blog.pojo.vo;

import com.blog.enums.NotificationType;
import com.blog.pojo.entity.FromUser;
import com.blog.pojo.entity.NotificationSourceData;
import lombok.Data;

import java.util.Date;

@Data
public class NotificationVo {
    private Long id;                         // 通知ID
    private NotificationType type;             // 通知类型
    private String title;                      // 通知标题
    private String content;                    // 通知内容
    private Integer isRead;                    // 是否已读
    private NotificationSourceData sourceData; // 来源数据（动态结构）
    private FromUser fromUser;                 // 触发用户信息
    private Date createTime;                 // 创建时间
}