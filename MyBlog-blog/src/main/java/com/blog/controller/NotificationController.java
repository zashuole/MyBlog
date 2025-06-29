package com.blog.controller;


import com.blog.pojo.vo.UnreadNotificationCountVo;
import com.blog.result.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @GetMapping("unread/count")
    public Result<Integer> getUnreadCount() {
        UnreadNotificationCountVo unreadNotificationCountVo = new UnreadNotificationCountVo();
        unreadNotificationCountVo.setTotal(50);
        unreadNotificationCountVo.setArticleLike(15);
        unreadNotificationCountVo.setCommentReport(15);
        unreadNotificationCountVo.setCommentReply(15);
        unreadNotificationCountVo.setCommentLike(3);
        unreadNotificationCountVo.setArticleComment(2);
        return Result.success(30);
    }
}
