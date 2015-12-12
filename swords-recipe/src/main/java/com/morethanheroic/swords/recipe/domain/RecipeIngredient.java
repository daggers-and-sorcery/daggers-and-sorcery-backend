package com.morethanheroic.swords.recipe.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains the data that how many and what ingredients are needed for a recipe.
 */
@Getter
@Builder
public class RecipeIngredient {

    private final int id;
    private final int amount;
}
