package com.morethanheroic.swords.forum.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ForumTopicEntity {

    private final int id;
    private final ForumCategoryEntity parent;
    private final String name;
    private final int commentCount;
    private final Date lastPostDate;
    private final UserEntity lastPoster;
    private final UserEntity creator;
}
