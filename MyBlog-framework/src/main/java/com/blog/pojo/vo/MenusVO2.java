package com.blog.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenusVO2 {
    private List<MenusVO2> children;
    private Long id;
    private String label;
    private Long parentId;
}
