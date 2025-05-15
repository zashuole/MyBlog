package com.blog.runner;

import com.blog.mapper.ArticleMapper;
import com.blog.utils.RedisCache;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  id viewCount
        Map<Long, Integer> map = articleMapper.getIdAndViewCount();
        redisCache.setCacheMap("articlu:viewCount",map);
        //存储到redis中
    }
}
