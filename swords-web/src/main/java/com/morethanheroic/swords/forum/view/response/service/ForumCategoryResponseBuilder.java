package com.morethanheroic.swords.forum.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingStartResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 2016. 08. 03..
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ForumResponseBuilder implements ResponseBuilder<ForumPartialResponseBuilderConfiguration> {

    @Autowired
    private final ResponseFactory responseFactory;
    private ForumPartialResponseBuilder forumPartialResponseBuilder;

    @Override
    public Response build(ForumPartialResponseBuilderConfiguration forumPartialResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumPartialResponseBuilderConfiguration.getUserEntity());

        response.setData("categories",forumPartialResponseBuilder.builder()
                .level(ForumPartialResponseBuilderConfiguration.getCategories())
                .build());

        return response;
    }
}
