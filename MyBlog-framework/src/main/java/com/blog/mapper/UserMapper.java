package com.blog.mapper;


import com.blog.annotation.AutoFill;
import com.blog.annotation.AutoFillRegister;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.dto.RegisterUserDto;
import com.blog.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from sys_user where user_name = #{name}")
    User getUserbyName(String name);

    @Select("select * from sys_user where id = #{userId}")
    User getById(Long userId);

    @AutoFill(OperationType.UPDATE)
    void update(User user);

    @Select("select * from sys_user where nick_name = #{nickName} or user_name = #{userName}")
    User getByNickNameorUserName(RegisterUserDto registerUserDto);

    @AutoFillRegister(OperationType.REGISTER)
    void insert(User user);
}
