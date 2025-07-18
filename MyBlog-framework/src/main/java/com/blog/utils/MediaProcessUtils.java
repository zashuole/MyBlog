package com.blog.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 媒体处理工具类
 * 使用JavaCV（基于FFmpeg）处理音视频文件
 *
 * @author blog
 * @since 2025-07-18
 */
@Component
public class MediaProcessUtils {

    /**
     * 提取媒体文件信息
     *
     * @param filePath 文件路径
     * @return 媒体信息
     */
    public Map<String, Object> extractMediaInfo(String filePath) {
        Map<String, Object> mediaInfo = new HashMap<>();
        FFmpegFrameGrabber grabber = null;

        try {
            grabber = new FFmpegFrameGrabber(filePath);
            grabber.start();

            // 基础信息
            mediaInfo.put("duration", grabber.getLengthInTime() / 1000000.0); // 转换为秒
            mediaInfo.put("bitrate", grabber.getVideoBitrate());
            
            // 视频信息
            if (grabber.getImageWidth() > 0 && grabber.getImageHeight() > 0) {
                mediaInfo.put("width", grabber.getImageWidth());
                mediaInfo.put("height", grabber.getImageHeight());
                mediaInfo.put("videoCodec", grabber.getVideoCodec());
                mediaInfo.put("mediaType", 2); // 视频类型
            }
            
            // 音频信息
            if (grabber.getAudioChannels() > 0) {
                mediaInfo.put("audioCodec", grabber.getAudioCodec());
                mediaInfo.put("audioChannels", grabber.getAudioChannels());
                mediaInfo.put("sampleRate", grabber.getSampleRate());
                
                // 如果没有视频信息，则为纯音频
                if (grabber.getImageWidth() <= 0) {
                    mediaInfo.put("mediaType", 3); // 音频类型
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("提取媒体信息失败: " + e.getMessage());
        } finally {
            if (grabber != null) {
                try {
                    grabber.stop();
                    grabber.release();
                } catch (Exception e) {
                    // 忽略关闭异常
                }
            }
        }

        return mediaInfo;
    }

    /**
     * 生成视频缩略图
     *
     * @param videoPath    视频文件路径
     * @param outputPath   输出图片路径
     * @param timeOffset   时间偏移（秒）
     * @return 是否成功
     */
    public boolean generateThumbnail(String videoPath, String outputPath, double timeOffset) {
        FFmpegFrameGrabber grabber = null;
        Java2DFrameConverter converter = null;

        try {
            grabber = new FFmpegFrameGrabber(videoPath);
            grabber.start();

            // 跳转到指定时间
            long timestamp = (long) (timeOffset * 1000000); // 转换为微秒
            grabber.setTimestamp(timestamp);

            // 获取帧
            Frame frame = grabber.grabImage();
            if (frame == null) {
                return false;
            }

            // 转换为BufferedImage
            converter = new Java2DFrameConverter();
            BufferedImage bufferedImage = converter.convert(frame);

            // 保存图片
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, "jpg", outputFile);

            return true;

        } catch (Exception e) {
            throw new RuntimeException("生成缩略图失败: " + e.getMessage());
        } finally {
            if (grabber != null) {
                try {
                    grabber.stop();
                    grabber.release();
                } catch (Exception e) {
                    // 忽略关闭异常
                }
            }
            if (converter != null) {
                converter.close();
            }
        }
    }

    /**
     * 检查文件是否为支持的媒体格式
     *
     * @param fileName 文件名
     * @return 是否支持
     */
    public boolean isSupportedMediaFormat(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        String extension = fileName.toLowerCase();
        
        // 支持的视频格式
        String[] videoFormats = {".mp4", ".avi", ".mov", ".wmv", ".flv", ".mkv", ".webm"};
        for (String format : videoFormats) {
            if (extension.endsWith(format)) {
                return true;
            }
        }

        // 支持的音频格式
        String[] audioFormats = {".mp3", ".wav", ".aac", ".ogg", ".flac", ".m4a"};
        for (String format : audioFormats) {
            if (extension.endsWith(format)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据文件扩展名判断媒体类型
     *
     * @param fileName 文件名
     * @return 媒体类型（2-视频，3-音频）
     */
    public int getMediaTypeByFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return 0;
        }

        String extension = fileName.toLowerCase();
        
        // 视频格式
        String[] videoFormats = {".mp4", ".avi", ".mov", ".wmv", ".flv", ".mkv", ".webm"};
        for (String format : videoFormats) {
            if (extension.endsWith(format)) {
                return 2; // 视频类型
            }
        }

        // 音频格式
        String[] audioFormats = {".mp3", ".wav", ".aac", ".ogg", ".flac", ".m4a"};
        for (String format : audioFormats) {
            if (extension.endsWith(format)) {
                return 3; // 音频类型
            }
        }

        return 0; // 未知类型
    }
}
