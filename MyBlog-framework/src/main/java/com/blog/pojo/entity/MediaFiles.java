package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体文件表(MediaFiles)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaFiles {

    private Long id;

    /**
     * 关联文章ID
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
     * 原始文件名
     */
    private String originalFilename;

    /**
     * 文件唯一标识（SHA-256）
     */
    private String fileIdentifier;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 媒体时长（秒）
     */
    private BigDecimal duration;

    /**
     * 视频宽度（像素）
     */
    private Integer width;

    /**
     * 视频高度（像素）
     */
    private Integer height;

    /**
     * 帧率
     */
    private BigDecimal fps;

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
     * 状态（0-上传中，1-转码中，2-已完成，3-失败）
     */
    private Integer status;

    /**
     * 转码进度（0-100）
     */
    private BigDecimal transcodeProgress;

    /**
     * 转码任务ID
     */
    private String transcodeTaskId;

    /**
     * 存储路径
     */
    private String storagePath;

    /**
     * CDN域名
     */
    private String cdnDomain;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
}
