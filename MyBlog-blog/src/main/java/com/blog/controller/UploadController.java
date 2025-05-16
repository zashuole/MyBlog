package com.blog.controller;


import com.blog.annotation.SystemLog;
import com.blog.result.Result;
import com.blog.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping
    @SystemLog(businessName = "上传图片到阿里云OSS")
        public Result<String> upload(MultipartFile img) throws IOException {
        String url = aliOSSUtils.upload(img);
        return Result.success(url);
    }
}
