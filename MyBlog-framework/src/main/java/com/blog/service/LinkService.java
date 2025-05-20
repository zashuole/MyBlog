package com.blog.service;

import com.blog.pojo.entity.Link;
import com.blog.pojo.vo.AllLinkVo;
import com.blog.result.PageBean;

import java.util.List;

public interface LinkService {
    List<AllLinkVo> getAllLink();

    PageBean list(int pageNum, int pageSize);

    void addLink(Link link);

    Link getLinkById(Long id);

    void update(Link link);

    void delete(Long id);

    void changeLinkStatus(Link link);
}
