package com.blog.mapper;

import com.blog.pojo.vo.AllLinkVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LinkMapper {
    @Select("select address,description,id,logo,name from link where status = 0")
    List<AllLinkVo> getAllLink();
}
