package com.blog.controller;


import com.blog.pojo.vo.AllLinkVo;
import com.blog.result.Result;
import com.blog.pojo.entity.Link;
import com.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("getAllLink")
    public Result<List<AllLinkVo>> getAllLink() {
        return Result.success(linkService.getAllLink());
    }
}
