package com.blog.mapper;


import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("select * from tag where del_flag = 0")
    List<Tag> list();
    @AutoFill(OperationType.INSERT)
    @Insert("insert into tag (name, create_by, create_time, update_by, update_time, del_flag, remark) VALUES (#{name},#{createBy},#{createTime},#{updateBy},#{updateTime},#{delFlag},#{remark})")
    void add(Tag tag);
    @AutoFill(OperationType.UPDATE)
    @Update("update tag set del_flag = #{id},update_by = #{updateBy},update_time = #{updateTime} where id = #{id}")
    void deleteById(Tag tag);
    @Select("select * from tag where id = #{id}")
    Tag getById(Long id);
    @Update("update tag set name = #{name},create_by = #{createBy},update_time = #{updateTime},create_time = #{createTime},remark = #{remark} where id = #{id}")
    void updateById(Tag tag);
}
