package com.blog.service.serviceImpl;

import com.blog.mapper.ArticleLikeMapper;
import com.blog.mapper.ArticleMapper;
import com.blog.pojo.entity.Article;
import com.blog.pojo.entity.ArticleLike;
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
        //点赞表删除记录
        articleLikeMapper.deleteByArticleId(articleId);
        ArticleLikeVo articleLikeVo = new ArticleLikeVo();
        articleLikeVo.setIsLiked(false);
        articleLikeVo.setLikeCount(article.getLikeCount());
        return articleLikeVo;
    }
}
