package com.morethanheroic.swords.recipe.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the item based requirements of a recipe.
 */
@Builder
@Getter
public class RecipeItemRequirement implements RecipeRequirement {

    private final ItemDefinition item;
    private final int amount;
}
