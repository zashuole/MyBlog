package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaUploadChunks;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体分片上传管理表(MediaUploadChunks)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaUploadChunksMapper extends BaseMapper<MediaUploadChunks> {
    // 继承BaseMapper，获得基础的CRUD方法
}
