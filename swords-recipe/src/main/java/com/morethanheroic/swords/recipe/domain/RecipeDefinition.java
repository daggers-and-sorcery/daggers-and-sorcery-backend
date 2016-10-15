package com.morethanheroic.swords.recipe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Contains the static data of a recipe.
 */
@Getter
@Builder
@ToString(of = {"id", "name", "type"})
public class RecipeDefinition {

    private final int id;
    private final int chance;
    private final String name;
    private final RecipeType type;
    private final List<RecipeIngredient> recipeIngredients;
    private final List<RecipeReward> recipeRewards;
    private final List<RecipeExperience> recipeExperiences;
    private final List<RecipeRequirement> recipeRequirements;
}
