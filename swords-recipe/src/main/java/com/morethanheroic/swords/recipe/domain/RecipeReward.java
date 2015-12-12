package com.morethanheroic.swords.recipe.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains the data that what items should a user get when successfully makes a recipe.
 */
@Getter
@Builder
public class RecipeReward {

    private final int id;
    private final int amount;
}
