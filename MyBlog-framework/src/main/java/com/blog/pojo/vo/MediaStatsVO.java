package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 媒体统计信息视图对象
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaStatsVO {
    
    /**
     * 播放次数
     */
    private Long playCount;
    
    /**
     * 独立观看用户数
     */
    private Long uniqueViews;
    
    /**
     * 平均观看百分比
     */
    private BigDecimal avgWatchPercent;
    
    /**
     * 平均观看时长（秒）
     */
    private BigDecimal avgWatchDuration;
    
    /**
     * 完成率（百分比）
     */
    private BigDecimal completionRate;
    
    /**
     * 最后播放位置（秒）
     */
    private BigDecimal lastPlayPosition;
    
    /**
     * 点赞数量
     */
    private Long likeCount;
    
    /**
     * 设备分布（百分比）
     */
    private DeviceDistributionVO deviceDistribution;
    
    /**
     * 清晰度分布（百分比）
     */
    private QualityDistributionVO qualityDistribution;
    
    /**
     * 热门片段
     */
    private List<PopularSegmentVO> popularSegments;
    
    /**
     * 设备分布内部类
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceDistributionVO {
        /**
         * 移动端百分比
         */
        private BigDecimal mobile;
        
        /**
         * 桌面端百分比
         */
        private BigDecimal desktop;
        
        /**
         * 平板端百分比
         */
        private BigDecimal tablet;
    }
    
    /**
     * 清晰度分布内部类
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QualityDistributionVO {
        /**
         * 1080p百分比
         */
        private BigDecimal quality1080p;
        
        /**
         * 720p百分比
         */
        private BigDecimal quality720p;
        
        /**
         * 480p百分比
         */
        private BigDecimal quality480p;
    }
    
    /**
     * 热门片段内部类
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PopularSegmentVO {
        /**
         * 开始时间（秒）
         */
        private Integer startTime;
        
        /**
         * 结束时间（秒）
         */
        private Integer endTime;
        
        /**
         * 热度分数
         */
        private BigDecimal heatScore;
        
        /**
         * 描述
         */
        private String description;
    }
}
