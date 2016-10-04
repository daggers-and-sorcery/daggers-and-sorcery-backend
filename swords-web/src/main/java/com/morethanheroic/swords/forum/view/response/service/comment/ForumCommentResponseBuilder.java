package com.morethanheroic.swords.forum.view.response.service.comment;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.comment.ForumCommentPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.comment.ForumCommentResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.service.category.ForumCategoryPartialResponseBuilder;
import com.morethanheroic.swords.forum.view.response.service.topic.ForumTopicPartialResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ForumCommentResponseBuilder implements ResponseBuilder<ForumCommentResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final ForumCategoryPartialResponseBuilder forumCategoryPartialResponseBuilder;
    private final ForumCommentPartialResponseBuilder forumCommentPartialResponseBuilder;
    private final ForumTopicPartialResponseBuilder forumTopicPartialResponseBuilder;

    @Override
    public Response build(ForumCommentResponseBuilderConfiguration forumCommentResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(forumCommentResponseBuilderConfiguration.getUserEntity());

        response.setData("category", forumCategoryPartialResponseBuilder.build(
                ForumCategoryPartialResponseBuilderConfiguration.builder()
                        .category(forumCommentResponseBuilderConfiguration.getCategory())
                        .build()
        ));
        response.setData("parent", forumTopicPartialResponseBuilder.build(
                ForumTopicResponseBuilderConfiguration.builder()
                        .forumTopicEntity(forumCommentResponseBuilderConfiguration.getTopic())
                        .build()
        ));
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
