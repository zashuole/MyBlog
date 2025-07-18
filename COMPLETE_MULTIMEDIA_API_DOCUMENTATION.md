# 多媒体博客系统完整API接口文档

## 📊 **数据库表设计与接口匹配分析**

### ✅ **匹配度分析**

| 接口需求 | 数据库表 | 匹配状态 | 说明 |
|---------|---------|---------|------|
| 媒体基本信息 | `article` + `media_files` | ✅ 完全匹配 | 双重存储，快速访问 |
| 多清晰度播放 | `media_qualities` | ✅ 完全匹配 | 支持多种清晰度 |
| 播放进度记录 | `media_play_progress` | ✅ 完全匹配 | 用户播放进度 |
| 播放统计数据 | `media_statistics` | ✅ 完全匹配 | 详细统计信息 |
| 关键帧预览 | `media_keyframes` | ✅ 完全匹配 | 视频预览缩略图 |
| 音频波形数据 | `media_waveforms` | ✅ 完全匹配 | 音频可视化 |
| 分片上传管理 | `media_upload_chunks` | ✅ 完全匹配 | 大文件上传 |
| 转码任务跟踪 | `media_transcode_tasks` | ✅ 完全匹配 | 转码状态管理 |

## 🔗 **完整API接口规范**

### **基础URL**: `http://localhost:7777`

### **统一返回格式**
```json
{
  "code": 200,        // 状态码：200=成功，500=失败
  "msg": "操作成功",   // 返回信息
  "data": {}          // 返回数据
}
```

---

## 1️⃣ **媒体信息管理**

### 1.1 获取媒体信息
```http
GET /media/info/{articleId}
```

**路径参数：**
- `articleId` (Long) - 文章ID

**数据库查询：**
```sql
SELECT 
    a.id as article_id,
    a.title as article_title,
    a.article_type,
    a.media_url,
    a.media_thumbnail,
    a.media_duration,
    a.has_media,
    mf.id as media_id,
    mf.title as media_title,
    mf.description,
    mf.duration,
    mf.thumbnail_url,
    mf.subtitle_url,
    mf.width,
    mf.height,
    mf.bitrate,
    mf.audio_codec,
    mf.video_codec
FROM article a
LEFT JOIN media_files mf ON a.id = mf.article_id AND mf.del_flag = 0
WHERE a.id = ? AND a.del_flag = 0;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "获取成功",
  "data": {
    "mediaId": 2001,
    "articleId": 1001,
    "mediaType": 2,
    "title": "SpringSecurity从入门到精通",
    "description": "SpringSecurity框架详细教程",
    "duration": 3600.00,
    "fileSize": 2147483648,
    "width": 1920,
    "height": 1080,
    "bitrate": 2500,
    "audioCodec": "aac",
    "videoCodec": "h264",
    "thumbnailUrl": "https://picsum.photos/320/180?random=1",
    "subtitleUrl": null,
    "qualities": [
      {
        "quality": "1080p",
        "label": "高清 1080P",
        "url": "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "bitrate": 2500,
        "fileSize": 2147483648,
        "width": 1920,
        "height": 1080
      },
      {
        "quality": "720p",
        "label": "标清 720P",
        "url": "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "bitrate": 1500,
        "fileSize": 1288490189,
        "width": 1280,
        "height": 720
      }
    ],
    "keyframes": [
      {
        "time": 0,
        "thumbnail": "https://picsum.photos/160/90?random=11"
      },
      {
        "time": 30,
        "thumbnail": "https://picsum.photos/160/90?random=12"
      }
    ],
    "waveform": [45, 67, 23, 89, 34, 78, 56, 90, 12, 67]
  }
}
```

### 1.2 获取媒体统计信息
```http
GET /media/stats/{articleId}
```

**路径参数：**
- `articleId` (Long) - 文章ID

**数据库查询：**
```sql
SELECT 
    mf.id as media_id,
    SUM(ms.play_count) as total_play_count,
    COUNT(DISTINCT mpr.user_id) as unique_views,
    AVG(ms.avg_watch_percent) as avg_watch_percent,
    AVG(ms.avg_watch_time) as avg_watch_time,
    SUM(ms.completion_count) as completion_count,
    SUM(ms.like_count) as like_count,
    mpp.current_time as last_play_position,
    SUM(ms.mobile_count) as mobile_count,
    SUM(ms.desktop_count) as desktop_count,
    SUM(ms.tablet_count) as tablet_count
FROM media_files mf
LEFT JOIN media_statistics ms ON mf.id = ms.media_id
LEFT JOIN media_play_records mpr ON mf.id = mpr.media_id
LEFT JOIN media_play_progress mpp ON mf.id = mpp.media_id AND mpp.user_id = ?
WHERE mf.article_id = ? AND mf.del_flag = 0
GROUP BY mf.id;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "获取成功",
  "data": {
    "playCount": 1250,
    "uniqueViews": 980,
    "avgWatchPercent": 75.5,
    "avgWatchDuration": 1350.8,
    "completionRate": 68.2,
    "lastPlayPosition": 450.5,
    "likeCount": 89,
    "deviceDistribution": {
      "mobile": 52.3,
      "desktop": 35.8,
      "tablet": 11.9
    },
    "qualityDistribution": {
      "1080p": 45.2,
      "720p": 38.7,
      "480p": 16.1
    },
    "popularSegments": [
      {
        "startTime": 120,
        "endTime": 180,
        "heatScore": 95.5,
        "description": "高潮片段"
      }
    ]
  }
}
```

---

## 2️⃣ **播放行为管理**

### 2.1 更新播放进度
```http
POST /media/progress
Content-Type: application/json
Authorization: Bearer {token}
```

**请求体：**
```json
{
  "mediaId": 2001,
  "currentTime": 450.5,
  "duration": 1800.5,
  "watchedPercent": 25.0,
  "quality": "720p",
  "playbackRate": 1.0,
  "sessionId": "session_abc123",
  "deviceInfo": {
    "userAgent": "Mozilla/5.0...",
    "screenResolution": "1920x1080",
    "networkType": "wifi",
    "deviceType": "desktop"
  }
}
```

**数据库操作：**
```sql
-- 更新播放进度
INSERT INTO media_play_progress (media_id, user_id, current_time, duration, watch_percent, quality, playback_rate)
VALUES (?, ?, ?, ?, ?, ?, ?)
ON DUPLICATE KEY UPDATE
current_time = VALUES(current_time),
watch_percent = VALUES(watch_percent),
quality = VALUES(quality),
playback_rate = VALUES(playback_rate),
last_update_time = NOW();

-- 记录播放行为
INSERT INTO media_play_records (media_id, article_id, user_id, session_id, device_type, quality, 
start_time, end_time, watch_duration, watch_percent, playback_rate, network_type, screen_resolution)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "进度更新成功",
  "data": {
    "progressId": 12345,
    "lastUpdateTime": "2024-01-15T10:35:00",
    "totalWatchTime": 1200.5
  }
}
```

### 2.2 获取播放历史
```http
GET /media/history?pageNum=1&pageSize=10&mediaType=2
Authorization: Bearer {token}
```

**查询参数：**
- `pageNum` (Integer, 默认1) - 页码
- `pageSize` (Integer, 默认10) - 每页大小
- `mediaType` (Integer, 可选) - 媒体类型筛选

**数据库查询：**
```sql
SELECT 
    mf.id as media_id,
    a.id as article_id,
    a.title,
    a.media_thumbnail,
    mf.duration,
    mpp.current_time as last_position,
    mpp.watch_percent,
    mpp.last_update_time as last_play_time,
    mf.media_type
FROM media_play_progress mpp
JOIN media_files mf ON mpp.media_id = mf.id
JOIN article a ON mf.article_id = a.id
WHERE mpp.user_id = ? 
  AND (? IS NULL OR mf.media_type = ?)
ORDER BY mpp.last_update_time DESC
LIMIT ? OFFSET ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "获取成功",
  "data": {
    "total": 25,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 3,
    "list": [
      {
        "mediaId": 2001,
        "articleId": 1001,
        "title": "SpringSecurity从入门到精通",
        "thumbnailUrl": "https://picsum.photos/320/180?random=1",
        "duration": 3600.5,
        "lastPlayTime": "2024-01-15T10:30:00",
        "lastPosition": 450.5,
        "watchPercent": 25.0,
        "mediaType": 2
      }
    ]
  }
}
```

---

## 3️⃣ **文件上传管理**

### 3.1 分片上传
```http
POST /media/upload/chunk
Content-Type: multipart/form-data
```

**请求参数：**
- `file` (MultipartFile) - 分片文件
- `chunkNumber` (Integer) - 分片序号 (1-based)
- `chunkSize` (Long) - 分片大小
- `currentChunkSize` (Long) - 当前分片实际大小
- `totalSize` (Long) - 文件总大小
- `identifier` (String) - 文件唯一标识(SHA-256)
- `filename` (String) - 原始文件名
- `totalChunks` (Integer) - 总分片数
- `articleId` (Long) - 关联文章ID
- `mediaType` (Integer) - 媒体类型

**数据库操作：**
```sql
-- 更新分片上传记录
INSERT INTO media_upload_chunks (file_identifier, filename, total_chunks, chunk_size, total_size, 
uploaded_chunks, upload_progress, article_id, media_type, user_id, status)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
ON DUPLICATE KEY UPDATE
uploaded_chunks = VALUES(uploaded_chunks),
upload_progress = VALUES(upload_progress),
update_time = NOW();
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "分片上传成功",
  "data": {
    "chunkNumber": 1,
    "uploaded": true,
    "progress": 10.5,
    "remainingChunks": 9
  }
}
```

### 3.2 检查上传状态
```http
GET /media/upload/check?identifier={fileHash}&filename={filename}
```

**查询参数：**
- `identifier` (String) - 文件唯一标识
- `filename` (String) - 文件名

**数据库查询：**
```sql
SELECT
    file_identifier,
    filename,
    total_chunks,
    uploaded_chunks,
    upload_progress,
    status
FROM media_upload_chunks
WHERE file_identifier = ? AND filename = ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "查询成功",
  "data": {
    "uploadedChunks": [true, true, false, false, true],
    "uploadProgress": 60.0,
    "needResume": true,
    "totalChunks": 5,
    "uploadedCount": 3
  }
}
```

### 3.3 合并分片
```http
POST /media/upload/merge
Content-Type: application/json
```

**请求体：**
```json
{
  "identifier": "abc123def456",
  "filename": "video.mp4",
  "articleId": 1001,
  "mediaType": 2,
  "title": "演示视频",
  "description": "这是一个演示视频"
}
```

**数据库操作：**
```sql
-- 创建媒体文件记录
INSERT INTO media_files (article_id, media_type, title, description, original_filename,
file_identifier, file_size, status, create_by)
VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?);

-- 创建转码任务
INSERT INTO media_transcode_tasks (task_id, media_id, source_file_path, target_qualities,
status, create_time)
VALUES (?, ?, ?, ?, 0, NOW());

-- 更新上传记录状态
UPDATE media_upload_chunks SET status = 2 WHERE file_identifier = ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "文件合并成功，开始转码",
  "data": {
    "mediaId": 2001,
    "transcodeTaskId": "task_abc123",
    "status": "transcoding",
    "estimatedTime": 300
  }
}
```

---

## 4️⃣ **转码管理**

### 4.1 查询转码状态
```http
GET /media/transcode/status/{taskId}
```

**路径参数：**
- `taskId` (String) - 转码任务ID

**数据库查询：**
```sql
SELECT
    mtt.task_id,
    mtt.media_id,
    mtt.progress,
    mtt.status,
    mtt.current_quality,
    mtt.error_message,
    mtt.start_time,
    mtt.end_time,
    mtt.estimated_remaining,
    mf.title,
    mf.duration,
    mf.width,
    mf.height
FROM media_transcode_tasks mtt
JOIN media_files mf ON mtt.media_id = mf.id
WHERE mtt.task_id = ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "查询成功",
  "data": {
    "taskId": "task_abc123",
    "status": "processing",
    "progress": 75.5,
    "currentStep": "生成720p版本",
    "estimatedRemaining": 120,
    "qualities": [
      {
        "quality": "1080p",
        "status": "completed",
        "fileSize": 157286400,
        "bitrate": 2500,
        "url": "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
      },
      {
        "quality": "720p",
        "status": "processing",
        "progress": 45.2
      }
    ],
    "thumbnail": {
      "status": "completed",
      "url": "https://picsum.photos/320/180?random=1",
      "count": 10
    },
    "metadata": {
      "duration": 1800.5,
      "width": 1920,
      "height": 1080,
      "fps": 30,
      "audioCodec": "aac",
      "videoCodec": "h264"
    }
  }
}
```

---

## 5️⃣ **媒体管理**

### 5.1 获取媒体列表
```http
GET /media/list/{articleId}
Authorization: Bearer {token}
```

**路径参数：**
- `articleId` (Long) - 文章ID

**数据库查询：**
```sql
SELECT
    mf.id as media_id,
    mf.title,
    mf.description,
    mf.original_filename,
    mf.media_type,
    mf.file_size,
    mf.duration,
    mf.thumbnail_url,
    mf.status,
    mf.transcode_progress,
    mf.create_time
FROM media_files mf
WHERE mf.article_id = ? AND mf.del_flag = 0
ORDER BY mf.create_time DESC;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "获取成功",
  "data": [
    {
      "mediaId": 1,
      "title": "演示视频",
      "filename": "demo-video.mp4",
      "mediaType": 2,
      "fileSize": 157286400,
      "duration": 300.5,
      "status": "completed",
      "transcodeProgress": 100,
      "thumbnailUrl": "https://picsum.photos/320/180?random=1",
      "createTime": "2024-01-15T10:30:00"
    }
  ]
}
```

### 5.2 更新媒体信息
```http
PUT /media/{mediaId}
Content-Type: application/json
Authorization: Bearer {token}
```

**路径参数：**
- `mediaId` (Long) - 媒体ID

**请求体：**
```json
{
  "title": "更新后的标题",
  "description": "更新后的描述",
  "mediaType": 2
}
```

**数据库操作：**
```sql
UPDATE media_files
SET title = ?, description = ?, media_type = ?, update_time = NOW()
WHERE id = ? AND create_by = ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "更新成功",
  "data": {
    "mediaId": 2001,
    "title": "更新后的标题",
    "description": "更新后的描述",
    "updateTime": "2024-01-15T10:35:00"
  }
}
```

### 5.3 删除媒体文件
```http
DELETE /media/{mediaId}
Authorization: Bearer {token}
```

**路径参数：**
- `mediaId` (Long) - 媒体ID

**数据库操作：**
```sql
-- 软删除媒体文件
UPDATE media_files SET del_flag = 1, update_time = NOW() WHERE id = ? AND create_by = ?;

-- 删除相关数据
DELETE FROM media_qualities WHERE media_id = ?;
DELETE FROM media_keyframes WHERE media_id = ?;
DELETE FROM media_waveforms WHERE media_id = ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "删除成功",
  "data": {
    "mediaId": 2001,
    "deletedFiles": [
      "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
      "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    ],
    "freedSpace": 314572800
  }
}
```

### 5.4 生成缩略图
```http
POST /media/thumbnail/{mediaId}?timeOffset=10
Authorization: Bearer {token}
```

**路径参数：**
- `mediaId` (Long) - 媒体ID

**查询参数：**
- `timeOffset` (Integer, 默认10) - 截图时间偏移（秒）

**数据库操作：**
```sql
UPDATE media_files
SET thumbnail_url = ?, update_time = NOW()
WHERE id = ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "缩略图生成成功",
  "data": {
    "thumbnailUrl": "https://picsum.photos/320/180?random=new",
    "width": 320,
    "height": 180,
    "fileSize": 15360,
    "generateTime": "2024-01-15T10:35:00"
  }
}
```

---

## 6️⃣ **流媒体播放**

### 6.1 获取音频波形数据
```http
GET /media/waveform/{mediaId}
```

**路径参数：**
- `mediaId` (Long) - 媒体ID

**数据库查询：**
```sql
SELECT
    media_id,
    sample_rate,
    channels,
    waveform_data,
    peaks_data,
    duration
FROM media_waveforms
WHERE media_id = ?;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "获取成功",
  "data": {
    "mediaId": 2001,
    "waveformData": [45, 67, 23, 89, 34, 78, 56, 90, 12, 67],
    "peaksData": [89, 90, 89, 90, 78],
    "duration": 300.5,
    "sampleRate": 44100,
    "channels": 2
  }
}
```

### 6.2 获取视频关键帧
```http
GET /media/keyframes/{mediaId}
```

**路径参数：**
- `mediaId` (Long) - 媒体ID

**数据库查询：**
```sql
SELECT
    time_offset,
    thumbnail_url,
    width,
    height
FROM media_keyframes
WHERE media_id = ?
ORDER BY time_offset ASC;
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "获取成功",
  "data": {
    "mediaId": 2001,
    "keyframes": [
      {
        "time": 0,
        "thumbnail": "https://picsum.photos/160/90?random=11",
        "width": 160,
        "height": 90
      },
      {
        "time": 30,
        "thumbnail": "https://picsum.photos/160/90?random=12",
        "width": 160,
        "height": 90
      }
    ],
    "interval": 30,
    "totalFrames": 10
  }
}
```

---

## 📊 **数据库表结构总结**

### **核心表关系**
```
article (1) ←→ (多) media_files (1) ←→ (多) media_qualities
                     ↓
              media_keyframes (多)
                     ↓
              media_waveforms (1)
                     ↓
              media_play_records (多)
                     ↓
              media_play_progress (多)
                     ↓
              media_statistics (多)
```

### **接口覆盖度**
- ✅ **媒体信息管理** - 100%覆盖
- ✅ **播放行为跟踪** - 100%覆盖
- ✅ **文件上传管理** - 100%覆盖
- ✅ **转码任务管理** - 100%覆盖
- ✅ **媒体内容管理** - 100%覆盖
- ✅ **流媒体功能** - 100%覆盖

## 🚀 **实施建议**

1. **优先级实现**：
   - P0: 媒体信息获取、播放进度记录
   - P1: 文件上传、转码管理
   - P2: 统计分析、高级功能

2. **性能优化**：
   - 媒体信息使用Redis缓存
   - 播放记录异步写入
   - 统计数据定时计算

3. **安全考虑**：
   - 文件上传权限验证
   - 媒体访问权限控制
   - 防盗链保护

这套API设计完全匹配数据库结构，可以支撑完整的多媒体博客系统！🎯
