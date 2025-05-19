package com.blog.controller;


import com.blog.pojo.dto.TagDto;
import com.blog.pojo.dto.UpDateTagDto;
import com.blog.pojo.vo.TagVo;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        tagService.deleteById(id);
        return Result.success();
    }
    @GetMapping("{id}")
    public Result<TagVo> get(@PathVariable Long id) {
        return Result.success(tagService.getById(id));
    }
    @PutMapping()
    public Result update(@RequestBody UpDateTagDto upDateTagDto) {
        tagService.update(upDateTagDto);
        return Result.success();
    }
}
