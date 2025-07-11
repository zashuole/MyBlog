package com.blog.mapper;


import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.Article;
import com.blog.pojo.vo.ArticleListVo;
import com.blog.pojo.vo.HotArticleVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    @Select("select id,title,view_count from article where status = 0 and del_flag = 0 order by view_count desc ")
    List<HotArticleVo> hotArticleList();

    Page<ArticleListVo> articleList(Long categoryId);

    @Select("select * from article where id = #{id}")
    Article getArticleDetail(Long id);

    @Select("SELECT id, view_count FROM article")
    List<Map<String, Object>> getIdAndViewCountRaw();

    @Update("UPDATE article SET view_count = #{viewCount} WHERE id = #{id}")
    void updateViewCountById(@Param("id") Long id, @Param("viewCount") Integer viewCount);

    @AutoFill(OperationType.INSERT)
    void addArticle(Article article);

    @Select("select * from article")
    Page<ArticleListVo> list();

    @AutoFill(OperationType.UPDATE)
    void updateArticle(Article article);

    @Select("select id from article where title = #{title}")
    Long getIdByTitle(String title);

    @Select("select * from article where title = #{title}")
    Article getArticleByTitle(String title);

    @Delete("delete from article where id = #{id}")
    void deleteArticleById(Long id);

    Page<ArticleListVo> articleListWithNoKeyWordAndCategoryId();

    Page<ArticleListVo> articleListWithKeyWordAndNoCategory(String keyword);

    Page<ArticleListVo> articleListWithKeyWordAndCategory( @Param("keyword") String keyword,
                                                           @Param("categoryId") Long categoryId);
    @Select("select id,title,view_count from article where status = 0 and del_flag = 0 and category_id = #{categoryId} order by view_count desc ")
    List<HotArticleVo> hotArticleListAndClass(Long categoryId);

    @Select("select * from article where id = #{articleId}")
    Article getCreateByarticleId(Long articleId);
}
