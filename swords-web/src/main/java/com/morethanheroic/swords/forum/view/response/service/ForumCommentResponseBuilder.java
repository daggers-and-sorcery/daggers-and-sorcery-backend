package com.morethanheroic.swords.forum.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.forum.view.response.domain.ForumCommentPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.ForumCommentResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ForumCommentResponseBuilder implements ResponseBuilder<ForumCommentResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ForumCommentPartialResponseBuilder forumCommentPartialResponseBuilder;

    @Override
    public Response build(ForumCommentResponseBuilderConfiguration forumCommentResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumCommentResponseBuilderConfiguration.getUserEntity());

        response.setData("comments",
                forumCommentResponseBuilderConfiguration.getComments().stream()
                        .map(commentEntity -> forumCommentPartialResponseBuilder.build(
                                ForumCommentPartialResponseBuilderConfiguration.builder()
                                        .commentEntity(commentEntity)
                                        .build()
                                )
                        ).collect(Collectors.toList())
        );

        return response;
    }
}
