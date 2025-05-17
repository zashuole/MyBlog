package com.blog.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签(Tag)实体类
 *
 * @author makejava
 * @since 2025-05-17 20:14:09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag{

    private Long id;
    /**
    标签名
    **/
    private String name;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    /**
    删除标志（0代表未删除，1代表已删除）
    **/
    private Integer delFlag;
    /**
    备注
    **/
    private String remark;

}

