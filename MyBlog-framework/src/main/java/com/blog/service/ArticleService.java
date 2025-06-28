package com.blog.service;



import com.blog.pojo.dto.ArticleDto;
import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.ArticleVo;
import com.blog.pojo.vo.HotArticleVo;
import com.blog.result.PageBean;

import java.util.List;

public interface ArticleService {

    List<HotArticleVo> hotArticleList();

    PageBean articleList(int pageNum, int pageSize, Long categoryId);

    Article getArticleDetail(Long id);

    void updateViewCount(Long id);

    void addArtical(ArticleDto articleDto);

    PageBean list(int pageNum, int pageSize);

    ArticleVo getAdminArticleById(Long id);

    void updateArtical(ArticleDto articalDto);

    void deleteArticleById(Long id);

    PageBean search(Integer pageNum, Integer pageSize, String keyword, Long categoryId);
}
