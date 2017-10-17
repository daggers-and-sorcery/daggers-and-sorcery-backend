package com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.tailoring.recipe.service.domain.TailoringResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TailoringCraftResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final TailoringResult tailoringResult;
}