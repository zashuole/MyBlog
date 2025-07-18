package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体统计数据表(MediaStatistics)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaStatistics {

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
     * 统计日期
     */
    private Date statDate;

    /**
     * 播放次数
     */
    private Integer playCount;

    /**
     * 独立观看用户数
     */
    private Integer uniqueViews;

    /**
     * 总观看时长（秒）
     */
    private Long totalWatchTime;

    /**
     * 平均观看时长（秒）
     */
    private BigDecimal avgWatchTime;

    /**
     * 平均观看完成度（百分比）
     */
    private BigDecimal avgWatchPercent;

    /**
     * 完整观看次数
     */
    private Integer completionCount;

    /**
     * 点赞数量
     */
    private Integer likeCount;

    /**
     * 1080p播放次数
     */
    @TableField("quality_1080p_count")
    private Integer quality1080pCount;

    /**
     * 720p播放次数
     */
    @TableField("quality_720p_count")
    private Integer quality720pCount;

    /**
     * 480p播放次数
     */
    @TableField("quality_480p_count")
    private Integer quality480pCount;

    /**
     * 移动端播放次数
     */
    private Integer mobileCount;

    /**
     * 桌面端播放次数
     */
    private Integer desktopCount;

    /**
     * 平板端播放次数
     */
    private Integer tabletCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
