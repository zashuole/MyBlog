package com.blog.service;



import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.HotArticleVo;
import com.blog.result.PageBean;

import java.util.List;

public interface ArticleService {

    List<HotArticleVo> hotArticleList();

    PageBean articleList(int pageNum, int pageSize, Long categoryId);

    Article getArticleDetail(Long id);

    void updateViewCount(Long id);
}
