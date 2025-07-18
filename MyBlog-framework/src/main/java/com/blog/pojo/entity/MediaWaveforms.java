package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体波形数据表(MediaWaveforms)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaWaveforms {

    private Long id;

    /**
     * 媒体文件ID
     */
    private Long mediaId;

    /**
     * 采样率
     */
    private Integer sampleRate;

    /**
     * 声道数
     */
    private Integer channels;

    /**
     * 波形数据（JSON数组）
     */
    private String waveformData;

    /**
     * 峰值数据（用于快速渲染）
     */
    private String peaksData;

    /**
     * 音频时长（秒）
     */
    private BigDecimal duration;

    /**
     * 创建时间
     */
    private Date createTime;
}
