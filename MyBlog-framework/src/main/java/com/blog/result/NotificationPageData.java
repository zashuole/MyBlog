package com.blog.result;

import com.blog.pojo.vo.NotificationVo;
import lombok.Data;

import java.util.List;

@Data
public class NotificationPageData {
    private long total;
    private int pageNum;
    private int pageSize;
    private List<NotificationVo> rows;
}