package com.morethanheroic.swords.recipe.domain;

import com.morethanheroic.swords.recipe.repository.dao.RecipeDatabaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecipeEntity {

    @Getter
    private final RecipeDefinition recipeDefinition;

    private final RecipeDatabaseEntity recipeDatabaseEntity;
}
