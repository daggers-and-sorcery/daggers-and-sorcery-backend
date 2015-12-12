package com.morethanheroic.swords.recipe.domain;

import com.morethanheroic.swords.skill.SkillType;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the data that how many expereince should the user get when successfully finishing a recipe.
 */
@Getter
@Builder
public class RecipeExperience {

    private final SkillType skill;
    private final int amount;
}
