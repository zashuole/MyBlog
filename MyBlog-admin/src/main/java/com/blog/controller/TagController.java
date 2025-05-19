package com.blog.controller;


import com.blog.pojo.entity.Tag;
import com.blog.pojo.entity.TagDto;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public Result<PageBean> page(int pageNum, int pageSize) {
        return Result.success(tagService.page(pageNum,pageSize));
    }
    @PostMapping()
    public Result add(@RequestBody TagDto tagDto) {
        tagService.add(tagDto);
        return Result.success();
    }
}
