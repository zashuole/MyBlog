package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体播放记录表(MediaPlayRecords)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaPlayRecords {

    private Long id;

    /**
     * 媒体文件ID
     */
    private Long mediaId;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 用户ID（未登录用户为NULL）
     */
    private Long userId;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 用户代理信息
     */
    private String userAgent;

    /**
     * 设备类型（mobile/desktop/tablet）
     */
    private String deviceType;

    /**
     * 播放清晰度
     */
    private String quality;

    /**
     * 开始播放时间（秒）
     */
    private BigDecimal startTime;

    /**
     * 结束播放时间（秒）
     */
    private BigDecimal endTime;

    /**
     * 实际观看时长（秒）
     */
    private BigDecimal watchDuration;

    /**
     * 观看完成度（百分比）
     */
    private BigDecimal watchPercent;

    /**
     * 播放倍速
     */
    private BigDecimal playbackRate;

    /**
     * 是否完整观看（0-否，1-是）
     */
    private Integer isCompleted;

    /**
     * 网络类型（wifi/4g/5g等）
     */
    private String networkType;

    /**
     * 屏幕分辨率
     */
    private String screenResolution;

    /**
     * 播放时间
     */
    private Date playTime;
}
