package com.blog.service;

import com.blog.pojo.entity.Tag;
import com.blog.result.PageBean;

import java.util.List;

public interface TagService {
    PageBean page(int pageNum, int pageSize);
}
