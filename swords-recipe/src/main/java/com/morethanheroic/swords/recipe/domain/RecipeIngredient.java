package com.morethanheroic.swords.recipe.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeIngredient {

    private final int id;
    private final int amount;
}
