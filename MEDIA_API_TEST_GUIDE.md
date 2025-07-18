# 媒体信息接口测试指南

## 📋 已实现的功能

### ✅ 获取媒体信息接口
- **接口路径**: `GET /media/info/{articleId}`
- **功能**: 根据文章ID获取关联的媒体文件信息
- **技术栈**: JavaCV (基于FFmpeg) + MyBatis Plus

### ✅ 获取媒体统计信息接口
- **接口路径**: `GET /media/stats/{articleId}`
- **功能**: 根据文章ID获取媒体播放统计数据
- **技术栈**: MyBatis Plus + 复杂统计查询

### ✅ 更新播放进度接口
- **接口路径**: `POST /media/progress`
- **功能**: 实时记录用户播放进度，支持断点续播和多设备同步
- **技术栈**: MyBatis Plus + 播放行为分析

### ✅ 获取播放历史接口
- **接口路径**: `GET /media/history`
- **功能**: 获取用户的播放历史记录，支持分页和媒体类型筛选
- **技术栈**: MyBatis Plus + 分页查询 + 关联查询

## 🚀 测试步骤

### 1. 启动项目
```bash
# 确保数据库连接正常
# 启动 MyBlogApplication
```

### 2. 创建测试数据
使用测试接口创建演示数据：

**创建统计测试数据:**
```http
POST http://localhost:7777/media/test/stats/1
```

### 3. 测试获取媒体信息
**获取媒体信息:**
```http
GET http://localhost:7777/media/info/1
```

**获取媒体统计信息:**
```http
GET http://localhost:7777/media/stats/1
```

**更新播放进度:**
```http
POST http://localhost:7777/media/progress
Content-Type: application/json

{
  "articleId": 1,
  "mediaId": 1,
  "currentTime": 450.50,
  "duration": 3600.00,
  "position": 450,
  "watchPercent": 12.51,
  "quality": "720p",
  "deviceType": "desktop",
  "playbackRate": 1.0,
  "isCompleted": false,
  "sessionId": "session_1704567890123_abc123def",
  "mediaType": 2,
  "timestamp": "2024-01-15T10:35:00.123Z"
}
```

**获取播放历史:**
```http
GET http://localhost:7777/media/history?pageNum=1&pageSize=10&mediaType=2
```

**参数说明:**
- `pageNum`: 页码（默认1）
- `pageSize`: 每页大小（默认10）
- `mediaType`: 媒体类型筛选（可选，1=音频，2=视频）

## 📊 预期返回结果

### 视频媒体信息示例
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "mediaId": 1,
    "articleId": 1,
    "mediaType": 2,
    "title": "演示视频",
    "description": "这是一个演示视频文件",
    "duration": 1800.5,
    "fileSize": 157286400,
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
        "thumbnail": "https://picsum.photos/160/90?random=10"
      },
      {
        "time": 30,
        "thumbnail": "https://picsum.photos/160/90?random=11"
      }
    ]
  }
}
```

### 音频媒体信息示例
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "mediaId": 2,
    "articleId": 2,
    "mediaType": 3,
    "title": "演示音频",
    "description": "这是一个演示音频文件",
    "duration": 300.5,
    "fileSize": 5242880,
    "bitrate": 320,
    "audioCodec": "mp3",
    "waveform": [45, 67, 23, 89, 34, 78, 56, 90, 12, 67]
  }
}
```

### 媒体统计信息示例
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "playCount": 1250,
    "uniqueViews": 980,
    "avgWatchPercent": 75.5,
    "avgWatchDuration": 1350.8,
    "completionRate": 68.4,
    "lastPlayPosition": 0.0,
    "likeCount": 89,
    "deviceDistribution": {
      "mobile": 52.3,
      "desktop": 35.8,
      "tablet": 11.9
    },
    "qualityDistribution": {
      "quality1080p": 45.2,
      "quality720p": 38.7,
      "quality480p": 16.1
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

### 播放进度更新响应示例
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "success": true,
    "action": "updated",
    "progressId": 123,
    "currentTime": 450.50,
    "watchPercent": 12.51,
    "isCompleted": false,
    "timestamp": "2024-01-15T10:35:00.123Z",
    "achievement": "视频观看完成",
    "completionRate": 95.5
  }
}
```

### 播放历史记录响应示例
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "total": 156,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 16,
    "records": [
      {
        "historyId": 1001,
        "articleId": 1,
        "mediaId": 2001,
        "title": "SpringSecurity从入门到精通",
        "description": "详细教程...",
        "mediaType": 2,
        "thumbnailUrl": "https://picsum.photos/320/180?random=1",
        "duration": 3600.0,
        "currentTime": 1250.5,
        "watchPercent": 34.7,
        "isCompleted": false,
        "lastWatchTime": "2024-01-15T10:30:00",
        "watchCount": 1,
        "totalWatchTime": 1250.5,
        "quality": "720p",
        "deviceType": "unknown",
        "createTime": "2024-01-10T09:00:00"
      },
      {
        "historyId": 1002,
        "articleId": 2,
        "mediaId": 2002,
        "title": "Vue3 响应式原理深度解析",
        "mediaType": 2,
        "duration": 2400.0,
        "currentTime": 2400.0,
        "watchPercent": 100.0,
        "isCompleted": true,
        "lastWatchTime": "2024-01-14T16:45:00",
        "watchCount": 1,
        "totalWatchTime": 2400.0
      }
    ]
  }
}
```

### 删除播放历史响应示例

**删除单条记录:**
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "success": true,
    "message": "删除成功",
    "deletedId": 1001
  }
}
```

**清空所有记录:**
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "success": true,
    "message": "清空成功",
    "deletedCount": 15
  }
}
```

**权限错误示例:**
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "success": false,
    "message": "无权限删除此记录"
  }
}
```

## 🔧 技术实现要点

### 1. JavaCV集成
- 使用FFmpegFrameGrabber提取媒体元数据
- 支持视频缩略图生成
- 支持多种音视频格式

### 2. 数据库设计
- 媒体文件主表 (media_files)
- 多清晰度版本表 (media_qualities)
- 关键帧表 (media_keyframes)
- 波形数据表 (media_waveforms)

### 3. 错误处理
- 文章不存在检查
- 媒体文件不存在处理
- 异常信息友好返回

## 📝 注意事项

1. **数据库表**: 需要确保相关数据库表已创建
2. **依赖**: JavaCV依赖已添加到pom.xml
3. **测试数据**: 使用模拟数据进行演示，实际使用时需要真实的媒体文件
4. **性能**: 大文件处理时建议使用异步方式

## 🎯 下一步扩展

1. 实现真实文件上传和处理
2. 添加转码功能
3. 实现播放进度记录
4. 添加统计分析功能
