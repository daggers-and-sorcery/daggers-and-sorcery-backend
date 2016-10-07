package com.morethanheroic.swords.forum.view.response.domain.comment;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.domain.ForumCommentEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ForumCommentPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final ForumCommentEntity commentEntity;
}
