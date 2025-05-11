package com.blog.service.serviceImpl;

import com.blog.mapper.LinkMapper;
import com.blog.pojo.entity.Link;
import com.blog.pojo.vo.AllLinkVo;
import com.blog.service.LinkService;
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
}
