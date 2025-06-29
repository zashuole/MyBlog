package com.blog.service.serviceImpl;

import com.blog.enums.ReportReasonEnum;
import com.blog.exception.SystemException;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.CommentMapper;
import com.blog.mapper.CommentReportMapper;
import com.blog.mapper.UserMapper;
import com.blog.pojo.dto.CommentDto;
import com.blog.pojo.dto.CommentReportDto;
import com.blog.pojo.entity.*;
import com.blog.pojo.vo.CommentVo;
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

    @Override
    public void addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        //回复评论不会传递"to_comment_user_id"字段，需要处理"to_comment_user_id"字段
        if(comment.getRootId()!=-1){
            comment.setToCommentUserId(commentMapper.getUserIdByToCommentId(comment.getToCommentId()));
        }
        //其他字段采用公共字段填充
        comment.setDelFlag(0);
        commentMapper.addComment(comment);
//        // 创建评论后,如果不是自己的文章
//        Article article = articleMapper.getCreateByarticleId(comment.getArticleId());
//        if(!SecurityUtils.getUserId().equals(article.getCreateBy())){
//            //将评论内容存入notification表中
//            CommentNotification commentNotification = new CommentNotification();
//            commentNotification.setType("reply");
//            commentNotification.setFromUserId(SecurityUtils.getUserId());
//            commentNotification.setToUserId(article.getCreateBy());
//            commentNotification.setArticleId(article.getId());
//        }
    }

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
        return likeCount;
    }

    @Override
    public Result reportComment(CommentReportDto reportDto) {
        // 1. 参数校验
        if (reportDto == null || reportDto.getCommentId() == null) {
            return Result.error("评论不存在");
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
        return Result.success("举报成功");
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
