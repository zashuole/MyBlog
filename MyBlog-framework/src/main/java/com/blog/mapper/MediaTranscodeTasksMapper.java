package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaTranscodeTasks;
import org.apache.ibatis.annotations.Mapper;

/**
 * 媒体转码任务表(MediaTranscodeTasks)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaTranscodeTasksMapper extends BaseMapper<MediaTranscodeTasks> {
    // 继承BaseMapper，获得基础的CRUD方法
}
