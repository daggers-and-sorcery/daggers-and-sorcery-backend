package com.morethanheroic.swords.forum.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.forum.view.response.domain.ForumPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ForumCategoryResponseBuilder implements ResponseBuilder<ForumPartialResponseBuilderConfiguration> {


    private final ResponseFactory responseFactory;
    private final ForumCategoryPartialResponseBuilder forumCategoryPartialResponseBuilder;

    @Override
    public Response build(ForumPartialResponseBuilderConfiguration forumPartialResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumPartialResponseBuilderConfiguration.getUserEntity());

        response.setData("categories", forumCategoryPartialResponseBuilder.build(forumPartialResponseBuilderConfiguration));

        return response;
    }
}
