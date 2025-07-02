package com.blog.controller;


import com.blog.pojo.vo.NotificationQueryRequest;
import com.blog.pojo.vo.UnreadNotificationCountVo;
import com.blog.result.NotificationPageData;
import com.blog.result.Result;
import com.blog.service.NotificationService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("unread/count")
    public Result<UnreadNotificationCountVo> getUnreadCount() {

        return Result.success(notificationService.getUnreadCount());
    }
    @GetMapping("/list")
    public Result<NotificationPageData> list(NotificationQueryRequest notificationQueryRequest) {
        return Result.success(notificationService.list(notificationQueryRequest));
    }
    @PutMapping("/read/{notificationId}")
    public Result read(@PathVariable Long notificationId) {
        notificationService.read(notificationId);
        return Result.success();
    }
    @PutMapping("/read/batch")
    public Result readBatch() {
        notificationService.batch();
        return Result.success();
    }
    @DeleteMapping("{notificationId}")
    public Result delete(@PathVariable Long notificationId) {
        notificationService.delete(notificationId);
        return Result.success();
    }
    @DeleteMapping("/clear")
    public Result clear() {
        notificationService.clear();
        return Result.success();
    }
}
