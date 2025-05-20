package com.blog.service.serviceImpl;

import com.blog.mapper.LinkMapper;
import com.blog.pojo.entity.Link;
import com.blog.pojo.vo.AllLinkVo;
import com.blog.result.PageBean;
import com.blog.service.LinkService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;


    @Override
    public List<AllLinkVo> getAllLink() {
        return linkMapper.getAllLink();
    }

    @Override
    public PageBean list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Link> page = linkMapper.list();
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public void addLink(Link link) {
        link.setDelFlag(0);
        linkMapper.addLink(link);
    }

    @Override
    public Link getLinkById(Long id) {
        return linkMapper.getLinkById(id);
    }

    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }

    @Override
    public void delete(Long id) {
        linkMapper.delete(id);
    }

    @Override
    public void changeLinkStatus(Link link) {
        linkMapper.changeLinkStatus(link);
    }
}
