package com.morethanheroic.swords.recipe.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeResourceRequirement implements RecipeRequirement {

    private final RecipeResource resource;
    private final int amount;
}
