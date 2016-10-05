package com.morethanheroic.swords.forum.view.response.service.category;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumCategoryListResponseBuilder implements ResponseBuilder<ForumCategoryListPartialResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ForumCategoryListPartialResponseBuilder forumCategoryListPartialResponseBuilder;

    @Override
    public Response build(ForumCategoryListPartialResponseBuilderConfiguration forumCategoryListPartialResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumCategoryListPartialResponseBuilderConfiguration.getUserEntity());

        response.setData("categories", forumCategoryListPartialResponseBuilder.build(forumCategoryListPartialResponseBuilderConfiguration));

        return response;
    }
}
