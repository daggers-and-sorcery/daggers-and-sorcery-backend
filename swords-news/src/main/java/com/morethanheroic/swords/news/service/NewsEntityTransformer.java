package com.morethanheroic.swords.news.service;

import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.repository.dao.NewsDatabaseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class NewsEntityTransformer {

    public NewsEntity transform(NewsDatabaseEntity newsDatabaseEntity) {
        return NewsEntity.builder()
                .id(newsDatabaseEntity.getId())
                .releaseDate(newsDatabaseEntity.getReleaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .icon(newsDatabaseEntity.getIcon())
                .message(newsDatabaseEntity.getMessage())
                .title(newsDatabaseEntity.getTitle())
                .build();
    }
}
