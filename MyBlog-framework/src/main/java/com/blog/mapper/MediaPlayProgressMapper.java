package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaPlayProgress;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体播放进度表(MediaPlayProgress)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaPlayProgressMapper extends BaseMapper<MediaPlayProgress> {
    // 继承BaseMapper，获得基础的CRUD方法
}
