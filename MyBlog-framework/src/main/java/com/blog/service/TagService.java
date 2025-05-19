package com.blog.service;

import com.blog.pojo.entity.Tag;
import com.blog.pojo.entity.TagDto;
import com.blog.result.PageBean;

import java.util.List;

public interface TagService {
    PageBean page(int pageNum, int pageSize);

    void add(TagDto tagDto);
}
