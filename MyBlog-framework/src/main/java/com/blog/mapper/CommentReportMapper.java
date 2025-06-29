package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.CommentReport;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentReportMapper {

    void save(CommentReport commentReport);
}
