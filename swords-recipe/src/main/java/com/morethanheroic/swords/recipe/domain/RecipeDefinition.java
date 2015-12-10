package com.morethanheroic.swords.recipe.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecipeDefinition {

    private final int id;
    private final int chance;
    private final RecipeType type;
    private final List<RecipeIngredient> recipeIngredients;
    private final List<RecipeReward> recipeRewards;
    private final List<RecipeExperience> recipeExperiences;
}
