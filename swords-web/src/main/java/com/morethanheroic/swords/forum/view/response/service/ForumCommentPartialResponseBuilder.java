package com.morethanheroic.swords.forum.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.forum.domain.ForumCommentEntity;
import com.morethanheroic.swords.forum.view.response.domain.ForumCommentPartialResponse;
import com.morethanheroic.swords.forum.view.response.domain.ForumCommentPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ForumCommentPartialResponseBuilder implements PartialResponseBuilder<ForumCommentPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ForumCommentPartialResponseBuilderConfiguration forumCommentPartialResponseBuilderConfiguration) {
        final ForumCommentEntity forumCommentEntity = forumCommentPartialResponseBuilderConfiguration.getCommentEntity();

        return ForumCommentPartialResponse.builder()
                .content(forumCommentEntity.getContent())
                .postDate(forumCommentEntity.getPostDate())
                .userName(forumCommentEntity.getPostUser().getUsername())
                .answerToCommentId(forumCommentEntity.isAnswer() ? forumCommentEntity.getAnswerToComment().getId() : -1)
                .build();
    }
}
