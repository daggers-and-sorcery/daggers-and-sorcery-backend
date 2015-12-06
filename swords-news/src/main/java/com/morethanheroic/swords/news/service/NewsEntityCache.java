package com.morethanheroic.swords.news.service;

import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.repository.domain.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Cache news entries.
 */
@Service
public class NewsEntityCache {

    @Autowired
    private NewsEntityTransformer newsEntityTransformer;

    @Autowired
    private NewsMapper newsMapper;

    private Map<Integer, NewsEntity> newsEntityMap = new HashMap<>();

    public NewsEntity getNewsEntity(int newsId) {
        if (!newsEntityMap.containsKey(newsId)) {
            loadNewsEntity(newsId);
        }

        return newsEntityMap.get(newsId);
    }

    //TODO: optimize this further if possible/needed
    public List<NewsEntity> getLastNewsEntity(int amount) {
        return newsMapper.findLast(amount).stream().map(newsDatabaseEntity -> getNewsEntity(newsDatabaseEntity.getId())).collect(Collectors.toList());
    }

    private void loadNewsEntity(int entityId) {
        final NewsEntity newsEntity = newsEntityTransformer.transform(newsMapper.findNewsEntity(entityId));

        newsEntityMap.put(newsEntity.getId(), newsEntity);
    }
}
