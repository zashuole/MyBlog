package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 媒体关键帧视图对象
 *
 * @author blog
 * @since 2025-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaKeyframeVO {
    
    /**
     * 时间偏移（秒）
     */
    private BigDecimal time;
    
    /**
     * 缩略图URL
     */
    private String thumbnail;
}
