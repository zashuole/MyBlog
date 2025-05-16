package com.blog.job;


import com.blog.annotation.SystemLog;
import com.blog.mapper.ArticleMapper;
import com.blog.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleMapper articleMapper;

    @Scheduled(cron = "0/60 * * * * ?")
    public void updateViewCount() {
        //获取redis的浏览量
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCount");
        //更新到数据库中
        for (Map.Entry<String, Integer> entry : cacheMap.entrySet()) {
            Long id = Long.valueOf(entry.getKey());
            Integer viewCount = entry.getValue();
            articleMapper.updateViewCountById(id, viewCount);
        }
    }

}
