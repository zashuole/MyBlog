package com.blog.mapper;

import com.blog.pojo.entity.ArticleLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleLikeMapper {

    @Select("select * from article_like where article_id = #{articleId}")
    ArticleLike getLikeByArticleId(Long articleId);

    @Insert("INSERT INTO article_like(article_id, user_id, create_time) " +
            "VALUES(#{articleId}, #{userId}, #{createTime})")
    void save(ArticleLike articleLike);
    @Delete("DELETE FROM article_like WHERE article_id = #{articleId}")
    void deleteByArticleId(Long articleId);
}
