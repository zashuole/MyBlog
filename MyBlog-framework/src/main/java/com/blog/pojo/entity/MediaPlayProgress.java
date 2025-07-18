package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体播放进度表(MediaPlayProgress)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaPlayProgress {

    private Long id;

    /**
     * 媒体文件ID
     */
    private Long mediaId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 当前播放位置（秒）
     */
    @TableField("`current_time`")
    private BigDecimal currentTime;

    /**
     * 媒体总时长（秒）
     */
    private BigDecimal duration;

    /**
     * 观看进度（百分比）
     */
    private BigDecimal watchPercent;

    /**
     * 播放清晰度
     */
    private String quality;

    /**
     * 播放倍速
     */
    private BigDecimal playbackRate;

    /**
     * 会话ID（用于未登录用户）- 非数据库字段
     */
    @TableField(exist = false)
    private String sessionId;

    /**
     * 设备类型 - 非数据库字段
     */
    @TableField(exist = false)
    private String deviceType;

    /**
     * 最后更新时间
     */
    @TableField("last_update_time")
    private Date lastUpdateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
