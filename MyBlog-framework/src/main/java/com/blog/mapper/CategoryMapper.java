package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.Category;
import com.blog.pojo.vo.AdminCategoryVo;
import com.blog.pojo.vo.CategoryVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select id,name from category")
    List<CategoryVo> getCategoryList();

    @Select("select id,name,description from category")
    List<AdminCategoryVo> getAdminCategoryList();

    @Select("select * from category where del_flag = 0")
    Page<Category> list();

    @AutoFill(OperationType.INSERT)
    void addCategory(Category category);

    @Select("select * from category where id = #{id}")
    Category getCategoryById(Long id);

    @AutoFill(OperationType.UPDATE)
    void updateCategory(Category category);

    @Update("update category set del_flag = 1 where id = #{id}")
    void deleteById(Long id);
}
