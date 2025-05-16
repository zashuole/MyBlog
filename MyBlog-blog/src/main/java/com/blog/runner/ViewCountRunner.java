package com.blog.runner;

import com.blog.mapper.ArticleMapper;
import com.blog.utils.RedisCache;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
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
        List<Map<String, Object>> rawList = articleMapper.getIdAndViewCountRaw();
        Map<String, Integer> map = new HashMap<>();
        for (Map<String, Object> rawmap : rawList) {
            String id = String.valueOf(((Number) rawmap.get("id")).longValue());
            Integer count = ((Number) rawmap.get("view_count")).intValue();
            map.put(id, count);
        }
        //存储到redis中
        redisCache.setCacheMap("article:viewCount",map);
    }
}
