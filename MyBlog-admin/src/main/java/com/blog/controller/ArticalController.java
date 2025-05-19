package com.blog.controller;

import com.blog.pojo.dto.ArticalDto;
import com.blog.result.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/article")
public class ArticalController {

    @Autowired
    private ArticleService articleService;

    @PostMapping()
    public Result addArtical(@RequestBody ArticalDto articalDto){
        articleService.addArtical(articalDto);
        return Result.success();
    }

}
