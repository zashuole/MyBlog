package com.blog.mapper;

import com.blog.pojo.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select id,name from category")
    List<CategoryVo> getCategoryList();
}
