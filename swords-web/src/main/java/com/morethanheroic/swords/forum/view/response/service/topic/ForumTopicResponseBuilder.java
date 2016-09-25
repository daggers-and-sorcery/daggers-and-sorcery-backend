package com.morethanheroic.swords.forum.view.response.service.topic;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicListResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumTopicResponseBuilder implements ResponseBuilder<ForumTopicListResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;

    @Override
    public Response build(final ForumTopicListResponseBuilderConfiguration forumTopicListResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumTopicListResponseBuilderConfiguration.getUserEntity());



        return response;
    }
}
