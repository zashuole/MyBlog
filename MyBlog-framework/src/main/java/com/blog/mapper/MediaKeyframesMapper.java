package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaKeyframes;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体关键帧表(MediaKeyframes)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaKeyframesMapper extends BaseMapper<MediaKeyframes> {
    // 继承BaseMapper，获得基础的CRUD方法
}
