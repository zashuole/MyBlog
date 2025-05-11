package com.blog.service.serviceImpl;

import com.blog.mapper.ArticleMapper;
import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.ArticleListVo;
import com.blog.pojo.vo.HotArticleVo;
import com.blog.result.PageBean;
import com.blog.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<HotArticleVo> hotArticleList() {
        return articleMapper.hotArticleList();
    }

    @Override
    public PageBean articleList(int pageNum, int pageSize, Long categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleListVo> list =  articleMapper.articleList(categoryId);
        return new PageBean(list.getTotal(), list.getResult());
    }

    @Override
    public Article getArticleDetail(Long id) {
        return articleMapper.getArticleDetail(id);
    }
}
