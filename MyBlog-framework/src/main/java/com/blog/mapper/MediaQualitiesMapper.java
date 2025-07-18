package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaQualities;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体清晰度版本表(MediaQualities)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaQualitiesMapper extends BaseMapper<MediaQualities> {
    // 继承BaseMapper，获得基础的CRUD方法
}
