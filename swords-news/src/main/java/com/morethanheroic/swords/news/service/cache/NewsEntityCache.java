package com.morethanheroic.swords.news.service.cache;

import com.morethanheroic.swords.definition.cache.EntityCache;
import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.repository.domain.NewsMapper;
import com.morethanheroic.swords.news.service.transformer.NewsEntityTransformer;
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
public class NewsEntityCache implements EntityCache<Integer, NewsEntity> {

    @Autowired
    private NewsEntityTransformer newsEntityTransformer;

    @Autowired
    private NewsMapper newsMapper;

    private Map<Integer, NewsEntity> newsEntityMap = new HashMap<>();

    public NewsEntity getEntity(Integer newsId) {
        if (!newsEntityMap.containsKey(newsId)) {
            loadNewsEntity(newsId);
        }

        return newsEntityMap.get(newsId);
    }

    //TODO: optimize this further if possible/needed
    public List<NewsEntity> getLastNewsEntity(int amount) {
        return newsMapper.findLast(amount).stream().map(newsDatabaseEntity -> getEntity(newsDatabaseEntity.getId())).collect(Collectors.toList());
    }

    private void loadNewsEntity(int entityId) {
        final NewsEntity newsEntity = newsEntityTransformer.transform(newsMapper.findNewsEntity(entityId));

        newsEntityMap.put(newsEntity.getId(), newsEntity);
    }
}
