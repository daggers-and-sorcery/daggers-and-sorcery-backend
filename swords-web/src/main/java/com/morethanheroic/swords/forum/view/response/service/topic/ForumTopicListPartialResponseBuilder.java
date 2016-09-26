package com.morethanheroic.swords.forum.view.response.service.topic;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.forum.domain.ForumTopicEntity;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicListResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.view.response.domain.topic.ForumTopicResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumTopicListPartialResponseBuilder implements PartialResponseCollectionBuilder<ForumTopicListResponseBuilderConfiguration> {

    private final ForumTopicPartialResponseBuilder forumTopicPartialResponseBuilder;

    @Override
    public List<PartialResponse> build(ForumTopicListResponseBuilderConfiguration forumTopicListResponseBuilderConfiguration) {
        final List<PartialResponse> result = new ArrayList<>();

        for (ForumTopicEntity forumTopicEntity : forumTopicListResponseBuilderConfiguration.getTopics()) {
            result.add(
                    forumTopicPartialResponseBuilder.build(
                            ForumTopicResponseBuilderConfiguration.builder()
                                    .userEntity(forumTopicListResponseBuilderConfiguration.getUserEntity())
                                    .forumTopicEntity(forumTopicEntity)
                                    .build()
                    )
            );
        }

        return result;
    }
}
