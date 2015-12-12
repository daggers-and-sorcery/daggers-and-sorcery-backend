package com.morethanheroic.swords.news.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.repository.dao.NewsDatabaseEntity;
import org.springframework.stereotype.Service;

/**
 * Transform a {@link NewsDatabaseEntity} to a {@link NewsEntity} domain object.
 */
@Service
public class NewsEntityTransformer implements DefinitionTransformer<NewsEntity, NewsDatabaseEntity> {

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
