package com.morethanheroic.swords.forum.view.response.service.topic;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.forum.domain.ForumTopicEntity;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicPartialResponse;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ForumTopicPartialResponseBuilder implements PartialResponseBuilder<ForumTopicResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ForumTopicResponseBuilderConfiguration forumTopicResponseBuilderConfiguration) {
        final ForumTopicEntity forumTopicEntity = forumTopicResponseBuilderConfiguration.getForumTopicEntity();

        return ForumTopicPartialResponse.builder()
                .id(forumTopicEntity.getId())
                .parentName(forumTopicEntity.getParent().getName())
                .commentCount(forumTopicEntity.getCommentCount())
                .creator(forumTopicEntity.getCreator().getUsername())
                .lastPostDate(forumTopicEntity.getLastPostDate().getTime())
                .lastPoster(forumTopicEntity.getLastPoster().getUsername())
                .name(forumTopicEntity.getName())
                .build();
    }
}
