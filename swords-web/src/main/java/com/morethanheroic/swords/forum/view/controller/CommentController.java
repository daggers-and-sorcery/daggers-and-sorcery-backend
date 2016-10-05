package com.morethanheroic.swords.forum.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.forum.domain.ForumTopicEntity;
import com.morethanheroic.swords.forum.repository.dao.CreateCommentContext;
import com.morethanheroic.swords.forum.service.ForumService;
import com.morethanheroic.swords.forum.view.request.domain.CreateCommentRequest;
import com.morethanheroic.swords.forum.view.response.domain.comment.ForumCommentResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.service.comment.ForumCommentResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final ForumService forumService;
    private final ResponseFactory responseFactory;
    private final ForumCommentResponseBuilder forumCommentResponseBuilder;

    @RequestMapping(value = "/forum/list/topic/{topicId}", method = RequestMethod.GET)
    public Response requestCommentList(UserEntity userEntity, @PathVariable int topicId) {
        final ForumTopicEntity parentTopic = forumService.getTopic(topicId);

        return forumCommentResponseBuilder.build(
                ForumCommentResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .category(parentTopic.getParent())
                        .topic(parentTopic)
                        .comments(forumService.getComments(topicId))
                        .build()
        );
    }

    /**
     * Create a new comment
     */
    @RequestMapping(value = "/forum/new_comment", method = RequestMethod.POST)
    public Response createNewComment(UserEntity userEntity, @RequestBody CreateCommentRequest createCommentRequest) {
        forumService.createNewComment(userEntity,
                CreateCommentContext.builder()
                        .answerToCommentId(createCommentRequest.getAnswerToCommentId())
                        .content(createCommentRequest.getContent())
                        .isAnswer(createCommentRequest.isAnswer())
                        .topicId(createCommentRequest.getParentTopic())
                        .build()
        );

        return responseFactory.newSuccessfulResponse(userEntity);
    }
}
