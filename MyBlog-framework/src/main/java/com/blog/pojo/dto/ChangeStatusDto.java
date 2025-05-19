package com.blog.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChangeStatusDto {
    private Integer roleId;
    private Integer status;
    /**
     更新者
     **/
    private Long updateBy;
    /**
     更新时间
     **/
    private Date updateTime;
}
