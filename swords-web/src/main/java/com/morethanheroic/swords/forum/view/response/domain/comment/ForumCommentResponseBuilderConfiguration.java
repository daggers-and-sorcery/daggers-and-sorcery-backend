package com.morethanheroic.swords.forum.view.response.domain.comment;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.domain.ForumCommentEntity;
import com.morethanheroic.swords.forum.domain.ForumTopicEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ForumCommentResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final ForumCategoryEntity category;
    private final ForumTopicEntity topic;
    private final List<ForumCommentEntity> comments;
}
