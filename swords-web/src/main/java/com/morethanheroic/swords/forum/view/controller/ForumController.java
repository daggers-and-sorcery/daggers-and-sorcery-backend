package com.morethanheroic.swords.forum.view.controller;

import com.google.common.collect.Lists;
import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.forum.repository.dao.NewComment;
import com.morethanheroic.swords.forum.repository.dao.NewTopic;
import com.morethanheroic.swords.forum.service.ForumService;
import com.morethanheroic.swords.forum.view.request.domain.NewCommentRequest;
import com.morethanheroic.swords.forum.view.response.domain.comment.ForumCommentResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.category.ForumCategoryListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.request.domain.NewTopicRequest;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicListResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.service.category.ForumCategoryListResponseBuilder;
import com.morethanheroic.swords.forum.view.response.service.comment.ForumCommentResponseBuilder;
import com.morethanheroic.swords.forum.view.response.service.topic.ForumTopicListResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
public class ForumController {

    @Autowired
    private ForumCategoryListResponseBuilder forumCategoryResponseBuilder;

    @Autowired
    private ForumCommentResponseBuilder forumCommentResponseBuilder;

    @Autowired
    private ForumTopicListResponseBuilder forumTopicListResponseBuilder;

    @Autowired
    private ForumCommentResponseBuilder forumCommentResponseBuilder;

    @Autowired
    private ForumService forumService;

    @Autowired
    private ResponseFactory responseFactory;

    /**
     * List all available categories.
     */
    @RequestMapping(value = "/forum/list/categories", method = RequestMethod.GET)
    public Response requestCategories(UserEntity userEntity) {
        return forumCategoryResponseBuilder.build
                (ForumCategoryListPartialResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .categories(forumService.getCategories())
                        .build()
                );
    }

    /**
     * Get comments for topic.
     */
    @RequestMapping(value = "/forum/list/topic/{topicId}", method = RequestMethod.GET)
    public Response requestSpecificTopicComments(UserEntity userEntity, @PathVariable int topicId) {
        return forumCommentResponseBuilder.build(
                ForumCommentResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .comments(forumService.getComments(topicId))
                        .build()
        );
    }

    /**
     * List topics in a certain category.
     */
    @RequestMapping(value = "/forum/list/category/{categoryId}", method = RequestMethod.GET)
    public Response requestSpecificCategory(UserEntity userEntity, @PathVariable int categoryId) {
        return forumTopicListResponseBuilder.build(
                ForumTopicListResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .topics(forumService.getTopics(categoryId))
                        .parentCategory(forumService.getCategory(categoryId))
                        .build()
        );
    }

    @RequestMapping(value = "/forum/list/topic/{topicId}", method = RequestMethod.GET)
    public Response requestSpecificTopic(UserEntity userEntity, @PathVariable int topicId) {
        return forumCommentResponseBuilder.build(
                ForumCommentResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .comments(forumService.getComments(topicId))
                        .build()
        );
    }

    /**
     * Create new topic
     */
    @RequestMapping(value = "/forum/new_topic", method = RequestMethod.POST)
    public Response createANewTopic(final UserEntity userEntity, final @RequestBody NewTopicRequest newTopicRequest) {
        forumService.createNewTopic(userEntity,
                NewTopic.builder()
                        .parentCategory(newTopicRequest.getParentCategory())
                        .content(newTopicRequest.getContent())
                        .name(newTopicRequest.getName())
                        .lastPostDate(Instant.now())
                        .lastPostUser(userEntity.getId())
                        .creator(userEntity.getId())
                        .build()
        );

        return responseFactory.newSuccessfulResponse(userEntity);
    }

    /**
     * Create a new comment
     */
    @RequestMapping(value = "/forum/new_comment", method = RequestMethod.POST)
    public Response createANewComment(UserEntity userEntity, @RequestBody NewCommentRequest newCommentRequest) {
        forumService.createNewComment(userEntity, NewComment.builder()
                .answerToCommentId(newCommentRequest.getAnswerToCommentId())
                .content(newCommentRequest.getContent())
                .isAnswer(newCommentRequest.isAnswer())
                .topicId(newCommentRequest.getTopicId())
                .build());

        return responseFactory.newSuccessfulResponse(userEntity);
    }
}
