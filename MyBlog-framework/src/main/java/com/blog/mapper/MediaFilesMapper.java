package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaFiles;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体文件表(MediaFiles)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaFilesMapper extends BaseMapper<MediaFiles> {
    // 继承BaseMapper，获得基础的CRUD方法
    // 如需自定义方法，可以在这里添加
}
