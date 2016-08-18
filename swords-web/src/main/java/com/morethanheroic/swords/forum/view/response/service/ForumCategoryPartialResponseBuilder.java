package com.morethanheroic.swords.forum.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.view.response.domain.ForumCategoryPartialResponse;
import com.morethanheroic.swords.forum.view.response.domain.ForumPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016. 08. 01..
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ForumCategoryPartialResponseBuilder implements PartialResponseCollectionBuilder<ForumPartialResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;

    @Override
    public List<PartialResponse> build(ForumPartialResponseBuilderConfiguration forumPartialResponseBuilderConfiguration) {
        List<PartialResponse> result = new ArrayList<>();

        for(ForumCategoryEntity forumCategoryEntity : forumPartialResponseBuilderConfiguration.getCategories()) {
            result.add(
                    ForumCategoryPartialResponse.builder()
                        .icon(forumCategoryEntity.getIcon())
                        .name(forumCategoryEntity.getName())
                        .postCount(forumCategoryEntity.getPostCount())
                        .lastPostDate(forumCategoryEntity.getLastPostDate())
                        .lastPoster(forumCategoryEntity.getLastPostUser().getUsername())
                        .build()
            );
        }



        return result;
    }
}