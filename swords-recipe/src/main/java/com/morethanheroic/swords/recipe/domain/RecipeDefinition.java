package com.morethanheroic.swords.recipe.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Contains the static data of a recipe.
 */
@Getter
@Builder
public class RecipeDefinition {

    private final int id;
    private final int chance;
    private final RecipeType type;
    private final List<RecipeIngredient> recipeIngredients;
    private final List<RecipeReward> recipeRewards;
    private final List<RecipeExperience> recipeExperiences;
    private final List<RecipeRequirement> recipeRequirements;
}
