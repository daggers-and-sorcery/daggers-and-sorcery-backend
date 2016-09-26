package com.morethanheroic.swords.forum.view.response.service.topic;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicListResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.service.category.ForumCategoryListPartialResponseBuilder;
import com.morethanheroic.swords.forum.view.response.service.category.ForumCategoryPartialResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumTopicListResponseBuilder implements ResponseBuilder<ForumTopicListResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ForumTopicListPartialResponseBuilder forumTopicListPartialResponse;
    private final ForumCategoryPartialResponseBuilder forumCategoryPartialResponseBuilder;

    @Override
    public Response build(final ForumTopicListResponseBuilderConfiguration forumTopicListResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumTopicListResponseBuilderConfiguration.getUserEntity());

        response.setData("parentCategory", forumCategoryPartialResponseBuilder.build(
                ForumCategoryPartialResponseBuilderConfiguration.builder()
                        .category(forumTopicListResponseBuilderConfiguration.getParentCategory())
                        .build()
        ));
        response.setData("topiclist", forumTopicListPartialResponse.build(forumTopicListResponseBuilderConfiguration));

        return response;
    }
}
