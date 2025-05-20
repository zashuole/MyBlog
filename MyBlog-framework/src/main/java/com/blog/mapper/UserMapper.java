package com.blog.mapper;


import com.blog.annotation.AutoFill;
import com.blog.annotation.AutoFillRegister;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.dto.RegisterUserDto;
import com.blog.pojo.entity.User;
import com.github.pagehelper.Page;
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

    @Select("select * from sys_user where del_flag = 0")
    Page<User> getlist();

    @AutoFill(OperationType.INSERT)
    void insertUser(User user);

    @Update("update sys_user set del_flag = 1 where id = #{userId}")
    void deleteById(Long userId);


    @AutoFill(OperationType.UPDATE)
    void updateWithNoPassWord(User user);
}
