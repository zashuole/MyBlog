package com.blog.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenusVo2 {
    private List<MenusVo2> children;
    private Long id;
    private String label;
    private Long parentId;
}
