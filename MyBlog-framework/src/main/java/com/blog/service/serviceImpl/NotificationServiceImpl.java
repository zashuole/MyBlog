package com.blog.service.serviceImpl;

import com.blog.enums.NotificationType;
import com.blog.mapper.*;
import com.blog.pojo.dto.CommentDto;
import com.blog.pojo.entity.*;
import com.blog.pojo.vo.NotificationQueryRequest;
import com.blog.pojo.vo.NotificationVo;
import com.blog.pojo.vo.NotificationsVo;
import com.blog.pojo.vo.UnreadNotificationCountVo;
import com.blog.result.NotificationPageData;
import com.blog.service.NotificationService;
import com.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentReportMapper commentReportMapper;
    @Override
    public UnreadNotificationCountVo getUnreadCount() {
        //获取当前用户
        Long userId = SecurityUtils.getUserId();
        //查询该用户的所有未读消息数量(不包括举报消息)
        Integer allMessageNumber = 0;
        //查询该用户的文章评论未读消息数量
        Integer articleCommentMessageNumber = notificationMapper.getMessageNumberByUserIdAndType(userId, NotificationType.ARTICLE_COMMENT);
        //查询该用户的文章点赞未读消息数量
        Integer articleLikeMessageNumber = notificationMapper.getMessageNumberByUserIdAndType(userId, NotificationType.ARTICLE_LIKE);
        //查询该用户的评论点赞未读消息数量
        Integer commentLikeMessageNumber = notificationMapper.getMessageNumberByUserIdAndType(userId, NotificationType.COMMENT_LIKE);
        //查询该用户的评论回复未读消息数量
        Integer commentReplyMessageNumber = notificationMapper.getMessageNumberByUserIdAndType(userId, NotificationType.COMMENT_REPLY);
        UnreadNotificationCountVo unreadNotificationCountVo = new UnreadNotificationCountVo();
        //查询该用户是不是管理员
        User user = userMapper.getById(userId);
        if(user.getType()!=null&&user.getType().equals("1")){
            //查询管理员用户的举报信息数量
            Integer commentReportMessageNumber = notificationMapper.getReportMessageNumberByUserIdAndType(NotificationType.COMMENT_REPORT);
            unreadNotificationCountVo.setCommentReport(commentReportMessageNumber);
            allMessageNumber += commentReportMessageNumber;
        }
        allMessageNumber += articleCommentMessageNumber+articleLikeMessageNumber+commentLikeMessageNumber+commentReplyMessageNumber;
        //赋值
        unreadNotificationCountVo.setTotal(allMessageNumber);
        unreadNotificationCountVo.setArticleComment(articleCommentMessageNumber);
        unreadNotificationCountVo.setArticleLike(articleLikeMessageNumber);
        unreadNotificationCountVo.setCommentLike(commentLikeMessageNumber);
        unreadNotificationCountVo.setCommentReply(commentReplyMessageNumber);
        return unreadNotificationCountVo;
    }

    @Override
    public NotificationPageData list(NotificationQueryRequest notificationQueryRequest) {
        //获取用户
        Long userId = SecurityUtils.getUserId();
        //创建相应信息
        NotificationPageData notificationPageData = new NotificationPageData();
        notificationPageData.setPageNum(notificationQueryRequest.getPageNum());
        notificationPageData.setPageSize(notificationQueryRequest.getPageSize());
        List<NotificationVo> notificationVoList = new ArrayList<>();

        if(notificationQueryRequest.getType()==null){
            //获取当前用户所有的信息
            List<CommentNotification> commentNotificationList = notificationMapper.getListByUserId(userId);
            //设置字段
            for (CommentNotification commentNotification : commentNotificationList) {
                NotificationVo notificationVo = new NotificationVo();
                notificationVo.setId(commentNotification.getId());
                notificationVo.setType(commentNotification.getType());
                notificationVo.setTitle(commentNotification.getTitle());
                notificationVo.setContent(commentNotification.getContent());
                notificationVo.setIsRead(commentNotification.getIsRead());
                NotificationSourceData notificationSourceData = new NotificationSourceData();
                switch (commentNotification.getType()) {
                    case ARTICLE_COMMENT:
                        //查询文章相关内容
                        Article article = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        //查询文章评论
                        CommentDto comment = commentMapper.getCommentById(commentNotification.getCommentId());
                        notificationSourceData.setArticleId(article.getId());
                        notificationSourceData.setArticleTitle(article.getTitle());
                        notificationSourceData.setCommentId(commentNotification.getCommentId());
                        notificationSourceData.setCommentContent(comment.getContent());
                        break;
                    case ARTICLE_LIKE:
                        Article articleLike = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        notificationSourceData.setTargetId(articleLike.getId());
                        notificationSourceData.setTargetTitle(articleLike.getTitle());
                        notificationSourceData.setTargetType("article");
                        break;
                    case COMMENT_LIKE:
                        Comment commentLike = commentMapper.getCommntById(commentNotification.getCommentId());
                        notificationSourceData.setTargetId(commentLike.getId());
                        notificationSourceData.setTargetTitle(commentLike.getContent());
                        notificationSourceData.setTargetType("comment");
                        break;
                    case COMMENT_REPLY:
                        //查询回复评论
                        Comment commentReply = commentMapper.getCommntById(commentNotification.getCommentId());
                        //查询回复评论所回复的评论
                        Comment commentToReple = commentMapper.getCommntById(commentReply.getToCommentId());
                        Article articleReply = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        notificationSourceData.setArticleId(articleReply.getId());
                        notificationSourceData.setArticleTitle(articleReply.getTitle());
                        notificationSourceData.setCommentId(commentReply.getId());
                        notificationSourceData.setCommentContent(commentToReple.getContent());
                        notificationSourceData.setReplyContent(commentReply.getContent());
                        break;
                    case COMMENT_REPORT:
                        Comment commentReport = commentMapper.getCommntById(commentNotification.getCommentId());
                        Article articleReport = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        //查询举报内容
                        CommentReport commentReport1 = commentReportMapper.getReportByCommentId(commentReport.getId());
                        notificationSourceData.setCommentId(commentReport.getId());
                        notificationSourceData.setReportReason(commentReport1.getReason());
                        notificationSourceData.setReportContent(commentReport1.getDescription());
                        notificationSourceData.setArticleId(articleReport.getId());
                        notificationSourceData.setArticleTitle(articleReport.getTitle());
                        break;
                }
                notificationVo.setSourceData(notificationSourceData);
                FromUser fromUser = new FromUser();
                fromUser.setId(commentNotification.getFromUserId());
                //查询触发者信息
                User user = userMapper.getById(commentNotification.getFromUserId());
                fromUser.setUsername(user.getUserName());
                fromUser.setAvatar(user.getAvatar());
                notificationVo.setFromUser(fromUser);
                notificationVoList.add(notificationVo);
            }
            notificationPageData.setRows(notificationVoList);
            notificationPageData.setTotal(notificationVoList.size());
            return notificationPageData;
        }else{
            //获取属于当前类型的通知信息
            //获取当前用户所有的信息
            List<CommentNotification> commentNotificationList = notificationMapper.getListByUserIdAndType(userId,notificationQueryRequest.getType().toUpperCase());
            //设置字段
            for (CommentNotification commentNotification : commentNotificationList) {
                NotificationVo notificationVo = new NotificationVo();
                notificationVo.setId(commentNotification.getId());
                notificationVo.setType(commentNotification.getType());
                notificationVo.setTitle(commentNotification.getTitle());
                notificationVo.setContent(commentNotification.getContent());
                notificationVo.setIsRead(commentNotification.getIsRead());
                NotificationSourceData notificationSourceData = new NotificationSourceData();
                switch (commentNotification.getType()) {
                    case ARTICLE_COMMENT:
                        //查询文章相关内容
                        Article article = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        //查询文章评论
                        CommentDto comment = commentMapper.getCommentById(commentNotification.getCommentId());
                        notificationSourceData.setArticleId(article.getId());
                        notificationSourceData.setArticleTitle(article.getTitle());
                        notificationSourceData.setCommentId(commentNotification.getCommentId());
                        notificationSourceData.setCommentContent(comment.getContent());
                        break;
                    case ARTICLE_LIKE:
                        Article articleLike = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        notificationSourceData.setTargetId(articleLike.getId());
                        notificationSourceData.setTargetTitle(articleLike.getTitle());
                        notificationSourceData.setTargetType("article");
                        break;
                    case COMMENT_LIKE:
                        Comment commentLike = commentMapper.getCommntById(commentNotification.getCommentId());
                        notificationSourceData.setTargetId(commentLike.getId());
                        notificationSourceData.setTargetTitle(commentLike.getContent());
                        notificationSourceData.setTargetType("comment");
                        break;
                    case COMMENT_REPLY:
                        //查询回复评论
                        Comment commentReply = commentMapper.getCommntById(commentNotification.getCommentId());
                        //查询回复评论所回复的评论
                        Comment commentToReple = commentMapper.getCommntById(commentReply.getToCommentId());
                        Article articleReply = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        notificationSourceData.setArticleId(articleReply.getId());
                        notificationSourceData.setArticleTitle(articleReply.getTitle());
                        notificationSourceData.setCommentId(commentReply.getId());
                        notificationSourceData.setCommentContent(commentToReple.getContent());
                        notificationSourceData.setReplyContent(commentReply.getContent());
                        break;
                    case COMMENT_REPORT:
                        Comment commentReport = commentMapper.getCommntById(commentNotification.getCommentId());
                        Article articleReport = articleMapper.getArticleDetail(commentNotification.getArticleId());
                        //查询举报内容
                        CommentReport commentReport1 = commentReportMapper.getReportByCommentId(commentReport.getId());
                        notificationSourceData.setCommentId(commentReport.getId());
                        notificationSourceData.setReportReason(commentReport1.getReason());
                        notificationSourceData.setReportContent(commentReport1.getDescription());
                        notificationSourceData.setArticleId(articleReport.getId());
                        notificationSourceData.setArticleTitle(articleReport.getTitle());
                        break;
                }
                notificationVo.setSourceData(notificationSourceData);
                FromUser fromUser = new FromUser();
                fromUser.setId(commentNotification.getFromUserId());
                //查询触发者信息
                User user = userMapper.getById(commentNotification.getFromUserId());
                fromUser.setUsername(user.getUserName());
                fromUser.setAvatar(user.getAvatar());
                notificationVo.setFromUser(fromUser);
                notificationVoList.add(notificationVo);
            }
            notificationPageData.setRows(notificationVoList);
            notificationPageData.setTotal(notificationVoList.size());
            return notificationPageData;
        }
    }
}
