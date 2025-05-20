package com.blog.controller;

import com.blog.pojo.entity.Link;
import com.blog.result.PageBean;
import com.blog.result.Result;
import com.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("//content//link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public Result<PageBean> list(int pageNum, int pageSize){
        return Result.success(linkService.list(pageNum,pageSize));
    }

    @PostMapping()
    public Result add(@RequestBody Link link){
        linkService.addLink(link);
        return Result.success();
    }

    @GetMapping("{id}")
    public Result<Link> getLinkById(@PathVariable Long id){
        return Result.success(linkService.getLinkById(id));
    }

    @PutMapping()
    public Result update(@RequestBody Link link){
        linkService.update(link);
        return Result.success();
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id){
        linkService.delete(id);
        return Result.success();
    }
    @PutMapping("/changeLinkStatus")
    public Result changeLinkStatus(@RequestBody Link link){
        linkService.changeLinkStatus(link);
        return Result.success();
    }
}
