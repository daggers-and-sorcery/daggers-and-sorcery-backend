package com.morethanheroic.swords.news.service;

import com.morethanheroic.swords.news.domain.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsFacade {

    @Autowired
    private NewsEntityCache newsEntityCache;

    public NewsEntity getNewsEntity(int newsId) {
        return newsEntityCache.getNewsEntity(newsId);
    }

    public List<NewsEntity> getLastNewsEntity(int amount) {
        return newsEntityCache.getLastNewsEntity(amount);
    }
}
