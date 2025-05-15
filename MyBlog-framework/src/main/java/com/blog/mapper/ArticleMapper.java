package com.blog.mapper;


import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.ArticleListVo;
import com.blog.pojo.vo.HotArticleVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    @Select("select id,title,view_count from article where status = 0 and del_flag = 0 order by view_count desc ")
    List<HotArticleVo> hotArticleList();

    Page<ArticleListVo> articleList(Long categoryId);

    @Select("select * from article where id = #{id}")
    Article getArticleDetail(Long id);
}
