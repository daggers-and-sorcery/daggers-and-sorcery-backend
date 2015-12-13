package com.morethanheroic.swords.recipe.domain;

import com.morethanheroic.swords.recipe.repository.dao.RecipeDatabaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecipeEntity {

    private final RecipeDefinition recipeDefinition;
    private final RecipeDatabaseEntity recipeDatabaseEntity;
}
