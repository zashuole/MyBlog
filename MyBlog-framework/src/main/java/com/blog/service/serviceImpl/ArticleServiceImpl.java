package com.blog.service.serviceImpl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.ArticleTagMapper;
import com.blog.pojo.dto.ArticalDto;
import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.ArticleListVo;
import com.blog.pojo.vo.HotArticleVo;
import com.blog.result.PageBean;
import com.blog.service.ArticleService;
import com.blog.utils.RedisCache;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<HotArticleVo> hotArticleList() {
        // 从数据库查询基础热点文章列表（带有默认的viewCount）
        List<HotArticleVo> list = articleMapper.hotArticleList();

        // 从Redis缓存中拿到最新的浏览量数据
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCount");

        // 遍历热点文章列表，更新访问量为缓存中的最新数据（如果存在）
        for (HotArticleVo vo : list) {
            Integer cachedViewCount = cacheMap.get(String.valueOf(vo.getId()));
            if (cachedViewCount != null) {
                vo.setViewCount(cachedViewCount.longValue());
            }
        }

        return list;
    }

    @Override
    public PageBean articleList(int pageNum, int pageSize, Long categoryId) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询文章列表
        Page<ArticleListVo> list = articleMapper.articleList(categoryId);

        // 从Redis缓存中获取访问量Map，假设key是字符串类型的文章id，value是访问量
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCount");

        // 遍历查询结果，更新viewCount字段为缓存中的访问量
        for (ArticleListVo article : list.getResult()) {
            String articleIdStr = String.valueOf(article.getId());
            if (cacheMap.containsKey(articleIdStr)) {
                article.setViewCount(cacheMap.get(articleIdStr).longValue());
            }
        }

        // 封装并返回分页结果
        return new PageBean(list.getTotal(), list.getResult());
    }

    @Override
    public Article getArticleDetail(Long id) {
        Article articleDetail = articleMapper.getArticleDetail(id);
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCount");
        Integer viewCount = cacheMap.get(id.toString());
        if (viewCount != null) {
            articleDetail.setViewCount(viewCount.longValue());
        }
        return articleDetail;
    }

    @Override
    public void updateViewCount(Long id) {
        //更新redis里的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
    }

    @Override
    public void addArtical(ArticalDto articalDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articalDto, article);
        article.setDelFlag(0);
        article.setViewCount(0L);
        //插入article并设置主键返回
        articleMapper.addArticle(article);
        Long articleId = article.getId();
        List<Long> tags = articalDto.getTags();
        for (Long tagId : tags) {
            articleTagMapper.insert(articleId,tagId);
        }
    }
}
