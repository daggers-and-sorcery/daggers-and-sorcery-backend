package com.morethanheroic.swords.forum.view.response.domain.category;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ForumCategoryPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final ForumCategoryEntity category;
}
