package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体分片上传管理表(MediaUploadChunks)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaUploadChunks {

    private Long id;

    /**
     * 文件唯一标识
     */
    private String fileIdentifier;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 总分片数
     */
    private Integer totalChunks;

    /**
     * 分片大小
     */
    private Long chunkSize;

    /**
     * 文件总大小
     */
    private Long totalSize;

    /**
     * 已上传分片列表（JSON数组）
     */
    private String uploadedChunks;

    /**
     * 上传进度（百分比）
     */
    private BigDecimal uploadProgress;

    /**
     * 关联文章ID
     */
    private Long articleId;

    /**
     * 媒体类型
     */
    private Integer mediaType;

    /**
     * 上传用户ID
     */
    private Long userId;

    /**
     * 状态（0-上传中，1-已完成，2-已合并，3-失败）
     */
    private Integer status;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
