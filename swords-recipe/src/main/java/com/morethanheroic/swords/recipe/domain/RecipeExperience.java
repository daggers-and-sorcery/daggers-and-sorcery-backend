package com.morethanheroic.swords.recipe.domain;

import com.morethanheroic.swords.skill.SkillType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeExperience {

    private final SkillType skill;
    private final int amount;
}
