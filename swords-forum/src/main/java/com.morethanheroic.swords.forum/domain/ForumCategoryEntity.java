package com.morethanheroic.swords.forum.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * Created by root on 2016. 08. 03..
 */
@Builder
@Getter
public class ForumCategoryEntity {

    private String name;
    private int postCount;
    private String icon;
    private Date lastPostDate;
    private UserEntity lastPostUser;
}
