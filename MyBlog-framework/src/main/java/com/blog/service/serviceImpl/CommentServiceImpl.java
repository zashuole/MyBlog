package com.blog.service.serviceImpl;

import com.blog.enums.NotificationType;
import com.blog.enums.ReportReasonEnum;
import com.blog.exception.SystemException;
import com.blog.mapper.*;
import com.blog.pojo.dto.ArticleCommentDto;
import com.blog.pojo.dto.CommentDto;
import com.blog.pojo.dto.CommentReportDto;
import com.blog.pojo.entity.*;
import com.blog.pojo.vo.CommentVo;
import com.blog.pojo.vo.NotificationsVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.CommentService;
import com.blog.utils.RedisCache;
import com.blog.utils.SecurityUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.blog.pojo.dto.CommentLikeDto;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentReportMapper commentReportMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentNotificationMapper commentNotificationMapper;

    @Override
    public PageBean commentList(int pageNum, int pageSize, Long articleId) {
        PageHelper.startPage(pageNum, pageSize);
        //根据id查询评论，但是为了封装成Vo对象，还需要给三个字段赋值，分别是"toCommentUserName"，"username“ "children
        Page<Comment> commentList = commentMapper.getCommentListByArticleId(articleId);
        // 创建 CommentVo 列表
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList.getResult()) {
            CommentVo commentVo = new CommentVo();
            //先把对应字段赋值
            BeanUtils.copyProperties(comment, commentVo);
            //首先根据creatBy来查询用户的昵称并赋值
            User user = userMapper.getById(comment.getCreateBy());
            commentVo.setUsername(user.getNickName());
            commentVo.setAvatar(user.getAvatar());
            if(comment.getToCommentId() ==-1){
                //查询所有的子评论
                List<Comment>  childenComment = getAllChildenCommentById(comment.getId());
                //List<Comment>  childenComment = commentMapper.getChildenCommentsById(comment.getId());
                List<CommentVo> childen = new ArrayList<>();
                //把子评论封装成对应的Vo对象
                for (Comment childComment : childenComment) {
                    CommentVo childCommentVo = new CommentVo();
                    BeanUtils.copyProperties(childComment, childCommentVo);
                    User childUser = userMapper.getById(childComment.getCreateBy());
                    childCommentVo.setUsername(childUser.getNickName());
                    childCommentVo.setAvatar(childUser.getAvatar());
                    User toUser = userMapper.getById(childComment.getToCommentUserId());
                    childCommentVo.setToCommentUserName(toUser.getNickName());
                    childen.add(childCommentVo);
                }
                //放入父评论的child字段中
                commentVo.setChildren(childen);
            }
            commentVoList.add(commentVo);
        }
        return new PageBean(commentList.getTotal(), commentVoList);
    }

    private List<Comment> getAllChildenCommentById(Long parentId) {
        List<Comment> result = new ArrayList<>();
        List<Comment> children = commentMapper.getChildenCommentsById(parentId);

        for (Comment child : children) {
            result.add(child); // 加入当前子评论
            result.addAll(getAllChildenCommentById(child.getId())); // 递归加入它的子评论
        }
        return result;
    }

//    @Override
//    public void addComment(CommentDto commentDto) {
//        Comment comment = new Comment();
//        BeanUtils.copyProperties(commentDto, comment);
//        //回复评论不会传递"to_comment_user_id"字段，需要处理"to_comment_user_id"字段
//        if(comment.getRootId()!=-1){
//            comment.setToCommentUserId(commentMapper.getUserIdByToCommentId(comment.getToCommentId()));
//        }
//        //其他字段采用公共字段填充
//        comment.setDelFlag(0);
//        commentMapper.addComment(comment);
//    }

    @Override
    public PageBean linkCommentList(int pageNum, int pageSize, Long articleId) {
        PageHelper.startPage(pageNum, pageSize);
        //根据id查询评论，但是为了封装成Vo对象，还需要给三个字段赋值，分别是"toCommentUserName"，"username“ "children
        Page<Comment> commentList = commentMapper.getLinkCommentListByArticleId(articleId);
        // 创建 CommentVo 列表
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList.getResult()) {
            CommentVo commentVo = new CommentVo();
            //先把对应字段赋值
            BeanUtils.copyProperties(comment, commentVo);
            //首先根据creatBy来查询用户的昵称并赋值
            User user = userMapper.getById(comment.getCreateBy());
            commentVo.setUsername(user.getNickName());
            commentVo.setAvatar(user.getAvatar());
            if(comment.getToCommentId() ==-1){
                //查询所有的子评论
                List<Comment>  childenComment = getAllChildenLinkCommentById(comment.getId());
                //List<Comment>  childenComment = commentMapper.getChildenCommentsById(comment.getId());
                List<CommentVo> childen = new ArrayList<>();
                //把子评论封装成对应的Vo对象
                for (Comment childComment : childenComment) {
                    CommentVo childCommentVo = new CommentVo();
                    BeanUtils.copyProperties(childComment, childCommentVo);
                    User childUser = userMapper.getById(childComment.getCreateBy());
                    childCommentVo.setUsername(childUser.getNickName());
                    childCommentVo.setAvatar(childUser.getAvatar());
                    User toUser = userMapper.getById(childComment.getToCommentUserId());
                    childCommentVo.setToCommentUserName(toUser.getNickName());
                    childen.add(childCommentVo);
                }
                //放入父评论的child字段中
                commentVo.setChildren(childen);
            }
            commentVoList.add(commentVo);
        }
        return new PageBean(commentList.getTotal(), commentVoList);
    }

    @Override
    public Long like(Long commentId) {
        Long likeCount = commentMapper.getLikeByCommentId(commentId);
        likeCount ++;
        String isLike = "1";
        commentMapper.saveLikeByCommentId(commentId,likeCount,isLike);
        //获取点赞的评论
        Comment comment = commentMapper.getCommntById(commentId);
        //自己不能给自己点赞
        if(!SecurityUtils.getUserId().equals(comment.getCreateBy())){
            CommentNotification isSuccess = commentNotificationMapper.getByCommentAndType(commentId,NotificationType.COMMENT_LIKE);
            if(isSuccess != null){
                commentNotificationMapper.likeByCommentAndType(commentId,NotificationType.COMMENT_LIKE);
            }else {
                User user = userMapper.getById(SecurityUtils.getUserId());
                CommentNotification commentNotification = new CommentNotification();
                commentNotification.setType(NotificationType.COMMENT_LIKE);
                commentNotification.setTitle("评论新点赞通知");
                commentNotification.setContent("用户"+user.getNickName()+"点赞了你的评论:"+comment.getContent());
                commentNotification.setFromUserId(user.getId());
                commentNotification.setToUserId(comment.getCreateBy());
                commentNotification.setArticleId(comment.getArticleId());
                commentNotification.setCommentId(commentId);
                commentNotification.setIsRead(0);
                commentNotification.setCreateTime(new Date());
                commentNotification.setUpdateTime(new Date());
                commentNotification.setDelFlag(0);
                commentNotificationMapper.save(commentNotification);
            }
        }
        return likeCount;
    }

    @Override
    public CommentLikeDto getCommentById(Long commentId) {
        CommentDto commentDto = commentMapper.getCommentById(commentId);
        String isLike = commentDto.getIsLiked();
        CommentLikeDto commentLikeDto = new CommentLikeDto();
        commentLikeDto.setLikeCount(commentDto.getLikeCount());
        if(isLike.equals("1")){
            commentLikeDto.setIsLiked(true);
        }else if(isLike.equals("0")) {
            commentLikeDto.setIsLiked(false);
        }
        return commentLikeDto;
    }

    @Override
    public Long unlike(Long commentId) {
        Long likeCount = commentMapper.getLikeByCommentId(commentId);
        likeCount --;
        String isLike = "0";
        commentMapper.saveLikeByCommentId(commentId,likeCount,isLike);
        //根据评论id找通知表并删除
        commentNotificationMapper.deleteByCommentIdAndType(commentId,NotificationType.COMMENT_LIKE);
        return likeCount;
    }

    @Override
    public Result reportComment(CommentReportDto reportDto) {
        // 1. 参数校验
        if (reportDto == null || reportDto.getCommentId() == null) {
            return Result.error("评论不存在");
        }
        //防止重复举报
        boolean hasReported = commentReportMapper.existsByUserIdAndCommentId(SecurityUtils.getUserId(), reportDto.getCommentId());
        if (hasReported) {
            return Result.error("不能重复举报");
        }
        //创建commentReport对象
        CommentReport commentReport = new CommentReport();
        commentReport.setReason(ReportReasonEnum.getDesc(reportDto.getReason()));
        commentReport.setCommentId(reportDto.getCommentId());
        commentReport.setDescription(reportDto.getDescription());
        //获取当前登录用户
        Long userId = SecurityUtils.getUserId();
        commentReport.setUserId(userId);
        commentReport.setStatus("0");
        commentReport.setDelFlag(0);
        commentReport.setCreateTime(new Date());
        commentReportMapper.save(commentReport);
        //这里自己也可以举报自己的评论
        User user = userMapper.getById(SecurityUtils.getUserId());
        //将举报内容存入notification表中
        CommentNotification commentNotification = new CommentNotification();
        commentNotification.setType(NotificationType.COMMENT_REPORT);
        commentNotification.setTitle("评论被举报通知");
        //查询被举报文章
        CommentDto comment = commentMapper.getCommentById(reportDto.getCommentId());
        Article article = articleMapper.getArticleDetail(comment.getArticleId());
        commentNotification.setContent(String.format("用户 %s 举报了你文章《%s》中的评论: %s，举报原因：%s",
                user.getNickName(),
                article.getTitle(),
                comment.getContent(),
                commentReport.getReason()));
        commentNotification.setFromUserId(user.getId());
        //举报评论交给管理员
        commentNotification.setToUserId(1L);
        commentNotification.setArticleId(article.getId());
        commentNotification.setIsRead(0);
        commentNotification.setCreateTime(new Date());
        commentNotification.setUpdateTime(new Date());
        commentNotification.setDelFlag(0);
        commentNotificationMapper.save(commentNotification);
        return Result.success("举报成功");
    }

    @Override
    public void sendComment(ArticleCommentDto articleCommentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(articleCommentDto, comment);
        //回复评论不会传递"to_comment_user_id"字段，需要处理"to_comment_user_id"字段
        if(comment.getRootId()!=-1){
            comment.setToCommentUserId(commentMapper.getUserIdByToCommentId(comment.getToCommentId()));
        }
        //其他字段采用公共字段填充
        comment.setDelFlag(0);
        comment.setArticleId(articleCommentDto.getArticleId());
        commentMapper.addComment(comment);
        User user = userMapper.getById(SecurityUtils.getUserId());
        //判断该评论是评论文章还是回复评论
        if(articleCommentDto.getRootId()==-1&&articleCommentDto.getToCommentId()==-1){
            //评论文章
            // 创建评论后,如果不是自己的文章
            Article article = articleMapper.getCreateByarticleId(comment.getArticleId());
            if(!user.getId().equals(article.getCreateBy())){
                //将评论内容存入notification表中
                CommentNotification commentNotification = new CommentNotification();
                commentNotification.setType(NotificationType.ARTICLE_COMMENT);
                commentNotification.setTitle("文章新评论通知");
                commentNotification.setContent(String.format("用户 %s 评论了你的文章《%s》：%s",
                        user.getNickName(),
                        article.getTitle(),
                        articleCommentDto.getContent()));
                commentNotification.setFromUserId(user.getId());
                commentNotification.setToUserId(article.getCreateBy());
                commentNotification.setArticleId(article.getId());
                commentNotification.setCommentId(comment.getId());
                commentNotification.setIsRead(0);
                commentNotification.setCreateTime(new Date());
                commentNotification.setUpdateTime(new Date());
                commentNotification.setDelFlag(0);
                commentNotificationMapper.save(commentNotification);
            }
        }else {
            //回复评论
            //获取被回复评论
            Comment commentReply = commentMapper.getCommentReplyById(articleCommentDto.getToCommentId());
            // 创建评论后,如果不是回复自己的评论
            if(!user.getId().equals(commentReply.getCreateBy())){
                //获取当前文章
                Article article = articleMapper.getCreateByarticleId(comment.getArticleId());
                //将评论内容存入notification表中
                CommentNotification commentNotification = new CommentNotification();
                commentNotification.setType(NotificationType.COMMENT_REPLY);
                commentNotification.setTitle("用户回复通知");
                //获取被回复的通知
                commentNotification.setContent(String.format("用户 %s 回复了你的评论%s：%s",
                        user.getNickName(),
                        commentReply.getContent(),
                        articleCommentDto.getContent()));
                commentNotification.setToUserId(commentReply.getCreateBy());
                commentNotification.setArticleId(article.getId());
                commentNotification.setCommentId(comment.getId());
                commentNotification.setReplyId(articleCommentDto.getToCommentId());
                commentNotification.setIsRead(0);
                commentNotification.setCreateTime(new Date());
                commentNotification.setFromUserId(user.getId());
                commentNotification.setUpdateTime(new Date());
                commentNotification.setDelFlag(0);
                commentNotificationMapper.save(commentNotification);
            }
        }

    }

    @Override
    public void keepComment(Long commentId) {
        commentMapper.keepCommentByCommentId(commentId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }

    private List<Comment> getAllChildenLinkCommentById(Long parentId) {
        List<Comment> result = new ArrayList<>();
        List<Comment> children = commentMapper.getChildenLinkCommentsById(parentId);

        for (Comment child : children) {
            result.add(child); // 加入当前子评论
            result.addAll(getAllChildenLinkCommentById(child.getId())); // 递归加入它的子评论
        }
        return result;
    }
}
