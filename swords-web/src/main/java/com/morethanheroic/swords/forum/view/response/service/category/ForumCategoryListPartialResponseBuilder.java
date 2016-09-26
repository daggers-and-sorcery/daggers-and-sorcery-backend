package com.morethanheroic.swords.forum.view.response.service.category;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponse;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumCategoryListPartialResponseBuilder implements PartialResponseCollectionBuilder<ForumCategoryListPartialResponseBuilderConfiguration> {

    private final ForumCategoryPartialResponseBuilder forumCategoryPartialResponseBuilder;

    @Override
    public List<PartialResponse> build(ForumCategoryListPartialResponseBuilderConfiguration forumCategoryListPartialResponseBuilderConfiguration) {
        List<PartialResponse> result = new ArrayList<>();

        for (ForumCategoryEntity forumCategoryEntity : forumCategoryListPartialResponseBuilderConfiguration.getCategories()) {
            result.add(forumCategoryPartialResponseBuilder.build(
                    ForumCategoryPartialResponseBuilderConfiguration.builder()
                            .category(forumCategoryEntity)
                            .build()
            ));
        }

        return result;
    }
}