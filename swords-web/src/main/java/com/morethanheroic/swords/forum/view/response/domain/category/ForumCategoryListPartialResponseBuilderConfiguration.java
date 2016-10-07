package com.morethanheroic.swords.forum.view.response.domain.category;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.forum.domain.ForumCategoryEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ForumCategoryListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private List<ForumCategoryEntity> categories;
}
