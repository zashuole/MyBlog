package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 媒体信息视图对象
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaInfoVO {
    
    /**
     * 媒体ID
     */
    private Long mediaId;
    
    /**
     * 文章ID
     */
    private Long articleId;
    
    /**
     * 媒体类型（1-音频，2-视频，3-多媒体）
     */
    private Integer mediaType;
    
    /**
     * 媒体标题
     */
    private String title;
    
    /**
     * 媒体描述
     */
    private String description;
    
    /**
     * 媒体时长（秒）
     */
    private BigDecimal duration;
    
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
    
    /**
     * 比特率（kbps）
     */
    private Integer bitrate;
    
    /**
     * 音频编码格式
     */
    private String audioCodec;
    
    /**
     * 视频编码格式
     */
    private String videoCodec;
    
    /**
     * 缩略图URL
     */
    private String thumbnailUrl;
    
    /**
     * 字幕文件URL
     */
    private String subtitleUrl;
    
    /**
     * 多清晰度版本列表
     */
    private List<MediaQualityVO> qualities;
    
    /**
     * 视频关键帧列表
     */
    private List<MediaKeyframeVO> keyframes;
    
    /**
     * 音频波形数据（仅音频类型）
     */
    private List<Integer> waveform;
    
    /**
     * 是否包含媒体文件
     */
    private Integer hasMedia;
}
