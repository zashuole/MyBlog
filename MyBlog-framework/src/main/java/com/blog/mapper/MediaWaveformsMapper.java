package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaWaveforms;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体波形数据表(MediaWaveforms)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaWaveformsMapper extends BaseMapper<MediaWaveforms> {
    // 继承BaseMapper，获得基础的CRUD方法
}
