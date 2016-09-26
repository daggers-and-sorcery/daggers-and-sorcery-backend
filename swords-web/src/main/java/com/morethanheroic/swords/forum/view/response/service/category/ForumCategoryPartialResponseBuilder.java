package com.morethanheroic.swords.forum.view.response.service.category;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponse;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ForumCategoryPartialResponseBuilder implements PartialResponseBuilder<ForumCategoryPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ForumCategoryPartialResponseBuilderConfiguration forumCategoryPartialResponseBuilderConfiguration) {
        final ForumCategoryEntity forumCategoryEntity = forumCategoryPartialResponseBuilderConfiguration.getCategory();

        return ForumCategoryPartialResponse.builder()
                        .id(forumCategoryEntity.getId())
                        .icon(forumCategoryEntity.getIcon())
                        .name(forumCategoryEntity.getName())
                        .postCount(forumCategoryEntity.getPostCount())
                        .lastPostDate(forumCategoryEntity.getLastPostDate())
                        .lastPoster(forumCategoryEntity.getLastPostUser() != null ? forumCategoryEntity.getLastPostUser().getUsername() : null)
                        .build();
    }
}
