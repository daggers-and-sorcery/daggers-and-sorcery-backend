package com.morethanheroic.swords.forum.view.response.service.category;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponse;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumCategoryPartialResponseBuilder implements PartialResponseCollectionBuilder<ForumCategoryPartialResponseBuilderConfiguration> {

    @Override
    public List<PartialResponse> build(ForumCategoryPartialResponseBuilderConfiguration forumCategoryPartialResponseBuilderConfiguration) {
        List<PartialResponse> result = new ArrayList<>();

        for (ForumCategoryEntity forumCategoryEntity : forumCategoryPartialResponseBuilderConfiguration.getCategories()) {
            result.add(
                    ForumCategoryPartialResponse.builder()
                            .id(forumCategoryEntity.getId())
                            .icon(forumCategoryEntity.getIcon())
                            .name(forumCategoryEntity.getName())
                            .postCount(forumCategoryEntity.getPostCount())
                            .lastPostDate(forumCategoryEntity.getLastPostDate())
                            .lastPoster(forumCategoryEntity.getLastPostUser() != null ? forumCategoryEntity.getLastPostUser().getUsername() : null)
                            .build()
            );
        }

        return result;
    }
}