package com.blog.pojo.vo;

import lombok.Data;

@Data
public class AdminCategoryVo {
    private Long id;
    /**
     分类名
     **/
    private String name;
    /**
     描述
     **/
    private String description;
}
