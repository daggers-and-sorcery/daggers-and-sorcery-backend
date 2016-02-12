package com.morethanheroic.swords.skill.cooking.service.experience.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import com.morethanheroic.swords.skill.domain.SkillType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeExperiencePartialResponse extends PartialResponse {

    private final String skill;
    private final int amount;
}
