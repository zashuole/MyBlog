package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.MediaPlayRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 媒体播放记录表(MediaPlayRecords)数据访问层
 *
 * @author blog
 * @since 2025-07-18
 */
@Mapper
public interface MediaPlayRecordsMapper extends BaseMapper<MediaPlayRecords> {

    /**
     * 统计独立观看用户数
     *
     * @param mediaId 媒体ID
     * @return 独立用户数
     */
    @Select("SELECT COUNT(DISTINCT user_id) FROM media_play_records WHERE media_id = #{mediaId} AND user_id IS NOT NULL")
    Long countUniqueUsers(@Param("mediaId") Long mediaId);
}
