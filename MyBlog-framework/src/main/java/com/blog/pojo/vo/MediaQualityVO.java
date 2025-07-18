package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 媒体清晰度版本视图对象
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaQualityVO {
    
    /**
     * 清晰度标识（1080p, 720p, 480p等）
     */
    private String quality;
    
    /**
     * 清晰度显示名称
     */
    private String label;
    
    /**
     * 播放文件URL
     */
    private String url;
    
    /**
     * 比特率（kbps）
     */
    private Integer bitrate;
    
    /**
     * 文件大小（字节）
     */
    private Long fileSize;
    
    /**
     * 视频宽度（像素）
     */
    private Integer width;
    
    /**
     * 视频高度（像素）
     */
    private Integer height;
}
