package com.morethanheroic.swords.forum.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ForumCategoryEntity {

    private int id;
    private String name;
    private int postCount;
    private String icon;
    private Date lastPostDate;
    private UserEntity lastPostUser;
}
