package com.blog.service.serviceImpl;

import com.blog.mapper.TagMapper;
import com.blog.pojo.entity.Tag;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> list() {
        return tagMapper.list();
    }
}
