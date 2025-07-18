package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 媒体清晰度版本表(MediaQualities)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaQualities {

    private Long id;

    /**
     * 媒体文件ID
     */
    private Long mediaId;

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
    private String fileUrl;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 比特率（kbps）
     */
    private Integer bitrate;

    /**
     * 视频宽度（像素）
     */
    private Integer width;

    /**
     * 视频高度（像素）
     */
    private Integer height;

    /**
     * 文件格式
     */
    private String format;

    /**
     * 状态（0-转码中，1-已完成，2-失败）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
