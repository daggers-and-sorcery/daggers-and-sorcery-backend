package com.morethanheroic.swords.news.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.news.service.cache.NewsEntityCache;
import com.morethanheroic.swords.news.view.response.service.LatestNewsResponseBuilder;
import com.morethanheroic.swords.news.view.response.service.domain.LatestNewsResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Access point for the news posts.
 */
@RestController
@RequiredArgsConstructor
public class LatestNewsController {

    private static final int NEWS_AMOUNT_PER_PAGE = 10;

    private final NewsEntityCache newsEntityCache;
    private final LatestNewsResponseBuilder latestNewsResponseBuilder;

    @ResponseBody
    @GetMapping("/news/last")
    public Response news(final UserEntity userEntity) {
        return latestNewsResponseBuilder.build(
                LatestNewsResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .news(newsEntityCache.getLastNewsEntity(NEWS_AMOUNT_PER_PAGE))
                        .build()
        );
    }
}
