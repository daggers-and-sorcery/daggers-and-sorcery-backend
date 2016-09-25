package com.morethanheroic.swords.forum.view.response.service.category;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumCategoryResponseBuilder implements ResponseBuilder<ForumCategoryPartialResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ForumCategoryPartialResponseBuilder forumCategoryPartialResponseBuilder;

    @Override
    public Response build(ForumCategoryPartialResponseBuilderConfiguration forumCategoryPartialResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumCategoryPartialResponseBuilderConfiguration.getUserEntity());

        response.setData("categories", forumCategoryPartialResponseBuilder.build(forumCategoryPartialResponseBuilderConfiguration));

        return response;
    }
}
