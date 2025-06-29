package com.blog.service.serviceImpl;

import com.blog.enums.NotificationType;
import com.blog.mapper.ArticleLikeMapper;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.CommentNotificationMapper;
import com.blog.mapper.UserMapper;
import com.blog.pojo.entity.Article;
import com.blog.pojo.entity.ArticleLike;
import com.blog.pojo.entity.CommentNotification;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.ArticleLikeVo;
import com.blog.service.ArticleLikeService;
import com.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleLikeServiceImpl implements ArticleLikeService {
    @Autowired
    private ArticleLikeMapper articleLikeMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentNotificationMapper commentNotificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public ArticleLikeVo likeStatus(Long articleId) {
        ArticleLike articleLike = articleLikeMapper.getLikeByArticleId(articleId);
        if(articleLike==null){
            //点赞表中没有记录，说明未点赞
            ArticleLikeVo articleLikeVo = new ArticleLikeVo();
            articleLikeVo.setIsLiked(false);
            articleLikeVo.setLikeCount(0L);
            return articleLikeVo;
        }else {
            //有记录，说明点过赞
            ArticleLikeVo articleLikeVo = new ArticleLikeVo();
            Article article = articleMapper.getArticleDetail(articleId);
            articleLikeVo.setIsLiked(true);
            articleLikeVo.setLikeCount(article.getLikeCount());
            return articleLikeVo;
        }
    }

    @Override
    public ArticleLikeVo likeArticle(Long articleId) {
        //获取article,并更新点赞数
        Article article = articleMapper.getArticleDetail(articleId);
        article.setLikeCount(article.getLikeCount()+1);
        articleMapper.updateArticle(article);
        //点赞表增加记录
        ArticleLike articleLike = new ArticleLike();
        articleLike.setArticleId(articleId);
        articleLike.setCreateTime(new Date());
        articleLike.setUserId(SecurityUtils.getUserId());
        articleLikeMapper.save(articleLike);
        //点赞成功后，把点赞成功的消息存入消息通知表
        User user = userMapper.getById(SecurityUtils.getUserId());
        //如果点赞的文章的用户和文章创造的用户不一样才发送通知
        if(!user.getId().equals(article.getCreateBy())){
            //查询通知表中是否有记录
            CommentNotification success = commentNotificationMapper.getByByArticleAndFromUser(articleId,SecurityUtils.getUserId());
            if(success!=null){//存在的话，直接设置del为0
                commentNotificationMapper.setDelTo0ByArticleAndFromUser(articleId,SecurityUtils.getUserId());
            }else {
                CommentNotification commentNotification = new CommentNotification();
                commentNotification.setType(NotificationType.ARTICLE_LIKE);
                commentNotification.setTitle("文章新点赞通知");
                commentNotification.setContent("用户"+user.getNickName()+"点赞了你的你的"+article.getTitle()+"文章");
                commentNotification.setFromUserId(user.getId());
                commentNotification.setToUserId(article.getCreateBy());
                commentNotification.setArticleId(articleId);
                commentNotification.setIsRead(0);
                commentNotification.setCreateTime(new Date());
                commentNotification.setUpdateTime(new Date());
                commentNotification.setDelFlag(0);
                commentNotificationMapper.save(commentNotification);
            }
        }

        ArticleLikeVo articleLikeVo = new ArticleLikeVo();
        articleLikeVo.setIsLiked(true);
        articleLikeVo.setLikeCount(article.getLikeCount());
        return articleLikeVo;
    }

    @Override
    public ArticleLikeVo unlikeArticle(Long articleId) {
        //获取article,并更新点赞数
        Article article = articleMapper.getArticleDetail(articleId);
        article.setLikeCount(article.getLikeCount()-1);
        articleMapper.updateArticle(article);
        //如果取消点赞，需要把通知点赞的给删除
        commentNotificationMapper.setDelTo1ByArticleAndFromUser(articleId,SecurityUtils.getUserId());
        //点赞表删除记录
        articleLikeMapper.deleteByArticleIdAndUserId(articleId, SecurityUtils.getUserId());
        ArticleLikeVo articleLikeVo = new ArticleLikeVo();
        articleLikeVo.setIsLiked(false);
        articleLikeVo.setLikeCount(article.getLikeCount());
        return articleLikeVo;
    }
}
