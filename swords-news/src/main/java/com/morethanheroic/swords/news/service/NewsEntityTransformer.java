package com.morethanheroic.swords.news.service;

import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.repository.dao.NewsDatabaseEntity;
import org.springframework.stereotype.Service;

@Service
public class NewsEntityTransformer {

    public NewsEntity transform(NewsDatabaseEntity newsDatabaseEntity) {
        return NewsEntity.builder()
                .id(newsDatabaseEntity.getId())
                .releaseDate(newsDatabaseEntity.getReleaseDate())
                .icon(newsDatabaseEntity.getIcon())
                .message(newsDatabaseEntity.getMessage())
                .title(newsDatabaseEntity.getTitle())
                .build();
    }
}
