package com.blog.service;

import com.blog.pojo.dto.TagDto;
import com.blog.pojo.dto.UpDateTagDto;
import com.blog.pojo.vo.TagVo;
import com.blog.result.PageBean;

import java.util.List;

public interface TagService {
    PageBean page(int pageNum, int pageSize);

    void add(TagDto tagDto);

    void deleteById(Long id);

    TagVo getById(Long id);

    void update(UpDateTagDto upDateTagDto);

    List<TagVo> listAllTag();
}
