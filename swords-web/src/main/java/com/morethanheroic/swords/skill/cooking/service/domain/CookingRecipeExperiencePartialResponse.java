package com.morethanheroic.swords.skill.cooking.service.domain;

import com.morethanheroic.swords.skill.domain.SkillType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeExperiencePartialResponse {

    private final SkillType skill;
    private final int amount;
}
