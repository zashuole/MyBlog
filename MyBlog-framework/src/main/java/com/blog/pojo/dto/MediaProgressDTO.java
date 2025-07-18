package com.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 媒体播放进度请求DTO
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaProgressDTO {
    
    /**
     * 文章ID
     */
    private Long articleId;
    
    /**
     * 媒体ID
     */
    private Long mediaId;
    
    /**
     * 当前播放位置（精确到0.01秒）
     */
    private BigDecimal currentTime;
    
    /**
     * 视频总时长
     */
    private BigDecimal duration;
    
    /**
     * 播放位置（整数，兼容旧版）
     */
    private Integer position;
    
    /**
     * 观看完成度（精确到0.01%）
     */
    private BigDecimal watchPercent;
    
    /**
     * 当前播放清晰度
     */
    private String quality;
    
    /**
     * 设备类型（mobile/tablet/desktop）
     */
    private String deviceType;
    
    /**
     * 播放速度
     */
    private BigDecimal playbackRate;
    
    /**
     * 是否观看完成（>95%自动标记）
     */
    private Boolean isCompleted;
    
    /**
     * 唯一会话ID
     */
    private String sessionId;
    
    /**
     * 媒体类型（1=音频，2=视频）
     */
    private Integer mediaType;
    
    /**
     * 时间戳
     */
    private String timestamp;
}
