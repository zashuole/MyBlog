package com.blog.service;

import com.blog.pojo.dto.MediaProgressDTO;
import com.blog.pojo.vo.MediaHistoryVO;
import com.blog.pojo.vo.MediaInfoVO;
import com.blog.pojo.vo.MediaStatsVO;
import com.blog.result.PageBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 媒体服务接口
 *
 * @author blog
 * @since 2025-07-18
 */
public interface MediaService {

    /**
     * 获取媒体信息
     *
     * @param articleId 文章ID
     * @return 媒体信息
     */
    MediaInfoVO getMediaInfo(Long articleId);

    /**
     * 获取媒体统计信息
     *
     * @param articleId 文章ID
     * @return 媒体统计信息
     */
    MediaStatsVO getMediaStats(Long articleId);

    /**
     * 更新播放进度
     *
     * @param progressDTO 播放进度数据
     * @return 更新结果
     */
    Map<String, Object> updatePlayProgress(MediaProgressDTO progressDTO);

    /**
     * 获取播放历史
     *
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @param mediaType 媒体类型
     * @return 播放历史分页数据
     */
    PageBean<MediaHistoryVO> getPlayHistory(Integer pageNum, Integer pageSize, Integer mediaType);

    /**
     * 分片上传
     *
     * @param file             分片文件
     * @param chunkNumber      分片序号
     * @param chunkSize        分片大小
     * @param currentChunkSize 当前分片实际大小
     * @param totalSize        文件总大小
     * @param identifier       文件唯一标识
     * @param filename         原始文件名
     * @param totalChunks      总分片数
     * @param articleId        关联文章ID
     * @param mediaType        媒体类型
     * @return 上传结果
     */
    Map<String, Object> uploadChunk(MultipartFile file, Integer chunkNumber, Long chunkSize,
                                    Long currentChunkSize, Long totalSize, String identifier,
                                    String filename, Integer totalChunks, Long articleId, Integer mediaType);

    /**
     * 检查上传状态
     *
     * @param identifier 文件唯一标识
     * @param filename   文件名
     * @return 上传状态
     */
    Map<String, Object> checkUploadStatus(String identifier, String filename);

    /**
     * 合并分片
     *
     * @param mergeData 合并数据
     * @return 合并结果
     */
    Map<String, Object> mergeChunks(Map<String, Object> mergeData);

    /**
     * 查询转码状态
     *
     * @param taskId 任务ID
     * @return 转码状态
     */
    Map<String, Object> getTranscodeStatus(String taskId);

    /**
     * 获取媒体列表
     *
     * @param articleId 文章ID
     * @return 媒体列表
     */
    Object getMediaList(Long articleId);

    /**
     * 更新媒体信息
     *
     * @param mediaId    媒体ID
     * @param updateData 更新数据
     * @return 更新结果
     */
    Map<String, Object> updateMediaInfo(Long mediaId, Map<String, Object> updateData);

    /**
     * 删除媒体文件
     *
     * @param mediaId 媒体ID
     * @return 删除结果
     */
    Map<String, Object> deleteMedia(Long mediaId);

    /**
     * 生成缩略图
     *
     * @param mediaId    媒体ID
     * @param timeOffset 时间偏移
     * @return 生成结果
     */
    Map<String, Object> generateThumbnail(Long mediaId, Integer timeOffset);

    /**
     * 获取音频波形数据
     *
     * @param mediaId 媒体ID
     * @return 波形数据
     */
    Map<String, Object> getWaveformData(Long mediaId);

    /**
     * 获取视频关键帧
     *
     * @param mediaId 媒体ID
     * @return 关键帧数据
     */
    Map<String, Object> getKeyframes(Long mediaId);
}
