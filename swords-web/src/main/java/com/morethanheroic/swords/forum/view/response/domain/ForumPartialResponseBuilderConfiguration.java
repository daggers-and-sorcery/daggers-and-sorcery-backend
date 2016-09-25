package com.morethanheroic.swords.forum.view.response.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.forum.repository.dao.ForumCategoriesDatabaseEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by root on 2016. 08. 01..
 */

@Getter
@Builder
public class ForumPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private List<ForumCategoryEntity> categories;

}
