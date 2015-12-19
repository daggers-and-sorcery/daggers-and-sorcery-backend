package com.morethanheroic.swords.recipe.domain;

import com.morethanheroic.swords.skill.domain.SkillType;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the skill based requirements of a recipe.
 */
@Getter
@Builder
public class RecipeRequirement {

    private final SkillType skill;
    private final int amount;
}
