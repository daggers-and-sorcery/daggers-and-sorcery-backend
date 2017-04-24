package com.morethanheroic.swords.news.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.news.view.response.service.domain.LatestNewsPartialResponse;
import com.morethanheroic.swords.news.view.response.service.domain.LatestNewsResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LatestNewsPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<LatestNewsResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(LatestNewsResponseBuilderConfiguration latestNewsResponseBuilderConfiguration) {
        return latestNewsResponseBuilderConfiguration.getNews().stream()
                .map(newsEntity -> LatestNewsPartialResponse.builder()
                        .title(newsEntity.getTitle())
                        .releaseDate(newsEntity.getReleaseDate())
                        .message(newsEntity.getMessage())
                        .icon(newsEntity.getIcon())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
