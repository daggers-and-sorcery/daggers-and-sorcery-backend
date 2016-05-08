package com.morethanheroic.swords.news.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.repository.dao.NewsDatabaseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

/**
 * Transform a {@link NewsDatabaseEntity} to a {@link NewsEntity} domain object.
 */
@Service
public class NewsEntityTransformer implements DefinitionTransformer<NewsEntity, NewsDatabaseEntity> {

    public NewsEntity transform(NewsDatabaseEntity newsDatabaseEntity) {
        return NewsEntity.builder()
                .id(newsDatabaseEntity.getId())
                .releaseDate(newsDatabaseEntity.getReleaseDate().atStartOfDay().atZone(ZoneId.of("GMT+0")).toInstant().toEpochMilli())
                .icon(newsDatabaseEntity.getIcon())
                .message(newsDatabaseEntity.getMessage())
                .title(newsDatabaseEntity.getTitle())
                .build();
    }
}
