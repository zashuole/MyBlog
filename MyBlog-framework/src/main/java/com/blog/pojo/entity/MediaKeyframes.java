package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体关键帧表(MediaKeyframes)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaKeyframes {

    private Long id;

    /**
     * 媒体文件ID
     */
    private Long mediaId;

    /**
     * 时间偏移（秒）
     */
    private BigDecimal timeOffset;

    /**
     * 缩略图URL
     */
    private String thumbnailUrl;

    /**
     * 缩略图宽度（像素）
     */
    private Integer width;

    /**
     * 缩略图高度（像素）
     */
    private Integer height;

    /**
     * 缩略图文件大小（字节）
     */
    private Integer fileSize;

    /**
     * 创建时间
     */
    private Date createTime;
}
