package com.blog.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MenuDisplayVo {
    /**
     菜单ID
     **/
    private Long id;
    /**
     字菜单
     */
    List<MenuDisplayVo> children;
    /**
     组件路径
     **/
    private String component;
    /**
     创建时间
     **/
    private Date createTime;
    /**
     菜单图标
     **/
    private String icon;
    /**
     菜单名称
     **/
    private String menuName;
    /**
     显示顺序
     **/
    private Integer orderNum;
    /**
     菜单类型（M目录 C菜单 F按钮）
     **/
    private String menuType;
    /**
     路由地址
     **/
    private String path;
    /**
     父菜单ID
     **/
    private Long parentId;
    /**
     菜单状态（0显示 1隐藏）
     **/
    private String visible;
    /**
     权限标识
     **/
    private String perms;
    /**
     菜单状态（0正常 1停用）
     **/
    private String status;
}
