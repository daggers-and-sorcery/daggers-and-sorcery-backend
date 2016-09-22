package com.morethanheroic.swords.forum.view.response.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.domain.ForumCommentEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by root on 2016. 09. 05..
 */
@Builder
@Getter
public class ForumCommentResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final List<ForumCommentEntity> comments;
}
