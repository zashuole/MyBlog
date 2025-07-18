package com.blog.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 媒体转码任务表(MediaTranscodeTasks)实体类
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaTranscodeTasks {

    private Long id;

    /**
     * 任务唯一标识
     */
    private String taskId;

    /**
     * 媒体文件ID
     */
    private Long mediaId;

    /**
     * 源文件路径
     */
    private String sourceFilePath;

    /**
     * 目标清晰度列表（JSON数组）
     */
    private String targetQualities;

    /**
     * 当前转码清晰度
     */
    private String currentQuality;

    /**
     * 转码进度（0-100）
     */
    private BigDecimal progress;

    /**
     * 状态（0-排队中，1-转码中，2-已完成，3-失败）
     */
    private Integer status;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 预计剩余时间（秒）
     */
    private Integer estimatedRemaining;

    /**
     * 执行节点
     */
    private String workerNode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
