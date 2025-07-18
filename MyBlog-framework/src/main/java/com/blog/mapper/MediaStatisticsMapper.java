package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体统计数据表(MediaStatistics)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaStatisticsMapper extends BaseMapper<MediaStatistics> {
    // 继承BaseMapper，获得基础的CRUD方法
}
