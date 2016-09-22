package com.morethanheroic.swords.forum.view.controller;

import com.google.common.collect.Lists;
import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.forum.repository.dao.NewComment;
import com.morethanheroic.swords.forum.repository.dao.NewTopic;
import com.morethanheroic.swords.forum.service.ForumService;
import com.morethanheroic.swords.forum.view.request.domain.NewCommentRequest;
import com.morethanheroic.swords.forum.view.response.domain.ForumCommentResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.ForumPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.request.domain.NewTopicRequest;
import com.morethanheroic.swords.forum.view.response.service.ForumCategoryResponseBuilder;
import com.morethanheroic.swords.forum.view.response.service.ForumCommentResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
public class ForumController {

    @Autowired
    private ForumCategoryResponseBuilder forumCategoryResponseBuilder;

    @Autowired
    private ForumCommentResponseBuilder forumCommentResponseBuilder;

    @Autowired
    private ForumService forumService;

    @Autowired
    private ResponseFactory responseFactory;

    /**
     * List all available categories
     */
    @RequestMapping(value = "/forum/list/categories", method = RequestMethod.GET)
    public Response requestCategories(UserEntity userEntity) {
        return forumCategoryResponseBuilder.build
                (ForumPartialResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .categories(forumService.getCategories())
                        .build()
                );
    }

    /**
     * Get comments for topic
     */
    @RequestMapping(value = "/forum/list/topic/{topicId}", method = RequestMethod.GET)
    public Response requestSpecificTopicComments(UserEntity userEntity, @PathVariable int topicId) {
        return forumCommentResponseBuilder.build(
                ForumCommentResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .comments(Lists.newArrayList())
                        .build()
        );
    }

    /**
     * List topics in a certain category (first 50 by default)
     */
  /*  @RequestMapping(value = "/forum/list/category/{category_id}", method = RequestMethod.GET)
    public Response requestSpecificCategory(UserEntity userEntity,@PathVariable int category_id){

    }*/

    /**
     *  Get certain topic
     */
  /*  @RequestMapping(value = "/forum/list/topic/{topic_id}", method = RequestMethod.GET)
    public Response requestSpecificTopic(UserEntity userEntity,@PathVariable int topic_id){


    }*/

    /**
     *  Get comments for topic
     */
  /*  @RequestMapping(value = "/forum/list/topic/{topic_id}/comments", method = RequestMethod.GET)
    public Response requestSpecificTopicComments(UserEntity userEntity,@PathVariable int topic_id){


    }*/


    /**
     * Create new topic
     */
    @RequestMapping(value = "/forum/new_topic", method = RequestMethod.POST)
    public Response createANewTopic(UserEntity userEntity, @RequestBody NewTopicRequest newTopicRequest) {


        forumService.createNewTopic(
                NewTopic.builder()
                        .parentCategory(newTopicRequest.getParentCategory())
                        .content(newTopicRequest.getContent())
                        .name(newTopicRequest.getName())
                        .commentCount(0)
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
