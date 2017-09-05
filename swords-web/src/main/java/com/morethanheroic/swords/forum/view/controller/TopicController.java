package com.morethanheroic.swords.forum.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.forum.repository.dao.CreateTopicContext;
import com.morethanheroic.swords.forum.service.ForumService;
import com.morethanheroic.swords.forum.view.request.domain.CreateTopicRequest;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicListResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.service.topic.ForumTopicListResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class TopicController {

    private final ForumService forumService;
    private final ResponseFactory responseFactory;
    private final ForumTopicListResponseBuilder forumTopicListResponseBuilder;

    /**
     * List topics in a certain category.
     */
    @GetMapping("/forum/list/category/{categoryId}")
    public Response requestTopicList(UserEntity userEntity, @PathVariable int categoryId) {
        return forumTopicListResponseBuilder.build(
                ForumTopicListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .topics(forumService.getTopics(categoryId))
                        .parentCategory(forumService.getCategory(categoryId))
                        .build()
        );
    }

    /**
     * Create new topic
     */
    @PostMapping("/forum/new_topic")
    public Response createTopic(final UserEntity userEntity, final @RequestBody CreateTopicRequest createTopicRequest) {
        forumService.createTopic(userEntity,
                CreateTopicContext.builder()
                        .parentCategory(createTopicRequest.getParentCategory())
                        .content(createTopicRequest.getContent())
                        .name(createTopicRequest.getName())
                        .lastPostDate(Instant.now())
                        .lastPostUser(userEntity.getId())
                        .creator(userEntity.getId())
                        .build()
        );

        return responseFactory.newSuccessfulResponse(userEntity);
    }
}
