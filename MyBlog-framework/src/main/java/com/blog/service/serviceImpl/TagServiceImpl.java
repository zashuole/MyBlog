package com.blog.service.serviceImpl;

import com.blog.mapper.TagMapper;
import com.blog.pojo.dto.UpDateTagDto;
import com.blog.pojo.entity.Tag;
import com.blog.pojo.dto.TagDto;
import com.blog.pojo.vo.TagVo;
import com.blog.result.PageBean;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Override
    public PageBean page(int pageNum, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        //查询所有标签
        List<Tag> list = tagMapper.list();
        // 使用 PageInfo 获取分页信息
        PageInfo<Tag> pageInfo = new PageInfo<>(list);

        List<TagVo> tagVo = new ArrayList<>();
        for (Tag tag : list) {
            TagVo tagVo1 = new TagVo();
            BeanUtils.copyProperties(tag, tagVo1);
            tagVo.add(tagVo1);
        }
        return new PageBean(pageInfo.getTotal(), tagVo);
    }

    @Override
    public void add(TagDto tagDto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDto, tag);
        tag.setDelFlag(0);
        tagMapper.add(tag);
    }

    @Override
    public void deleteById(Long id) {
        Tag tag = new Tag();
        tag.setId(id);
        tagMapper.deleteById(tag);
    }

    @Override
    public TagVo getById(Long id) {
        Tag tag = tagMapper.getById(id);
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }

    @Override
    public void update(UpDateTagDto upDateTagDto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(upDateTagDto, tag);
        tagMapper.updateById(tag);
    }
}
