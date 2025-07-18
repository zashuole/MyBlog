package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体播放历史记录视图对象
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaHistoryVO {
    
    /**
     * 历史记录ID（播放进度记录ID）
     */
    private Long historyId;
    
    /**
     * 文章ID
     */
    private Long articleId;
    
    /**
     * 媒体ID
     */
    private Long mediaId;
    
    /**
     * 媒体标题
     */
    private String title;
    
    /**
     * 媒体描述
     */
    private String description;
    
    /**
     * 媒体类型（1=音频，2=视频，3=多媒体）
     */
    private Integer mediaType;
    
    /**
     * 缩略图URL
     */
    private String thumbnailUrl;
    
    /**
     * 总时长（秒）
     */
    private BigDecimal duration;
    
    /**
     * 当前观看位置（秒）
     */
    private BigDecimal currentTime;
    
    /**
     * 观看完成度（百分比）
     */
    private BigDecimal watchPercent;
    
    /**
     * 是否看完
     */
    private Boolean isCompleted;
    
    /**
     * 最后观看时间
     */
    private Date lastWatchTime;
    
    /**
     * 观看次数（暂时设为1，后续可扩展）
     */
    private Integer watchCount;
    
    /**
     * 累计观看时长（暂时等于currentTime）
     */
    private BigDecimal totalWatchTime;
    
    /**
     * 最后播放清晰度
     */
    private String quality;
    
    /**
     * 最后播放设备类型
     */
    private String deviceType;
    
    /**
     * 首次观看时间
     */
    private Date createTime;
}
