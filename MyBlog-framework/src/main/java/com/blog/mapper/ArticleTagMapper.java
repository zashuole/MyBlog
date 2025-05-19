package com.blog.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleTagMapper {
    @Insert("INSERT INTO article_tag (article_id, tag_id) VALUES (#{articleId}, #{tagId})")
    void insert(@Param("articleId") Long articleId, @Param("tagId") Long tagId);

    @Select("select tag_id from article_tag where article_id = #{articleId}")
    List<Long> getTagsById(Long articleId);

    @Delete("delete from article_tag where article_id = #{articleId}")
    void deleteArticleMap(Long articleId);
}
