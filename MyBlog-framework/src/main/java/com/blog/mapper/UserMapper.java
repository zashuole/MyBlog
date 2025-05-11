package com.blog.mapper;


import com.blog.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from sys_user where user_name = #{name}")
    User getUserbyName(String name);
}
