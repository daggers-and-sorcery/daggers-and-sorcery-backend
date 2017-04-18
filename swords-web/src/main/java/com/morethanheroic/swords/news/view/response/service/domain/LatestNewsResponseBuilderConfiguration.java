package com.morethanheroic.swords.news.view.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class LatestNewsResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final List<NewsEntity> news;
}
