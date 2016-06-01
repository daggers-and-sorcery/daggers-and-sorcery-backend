package com.morethanheroic.swords.skill.leatherworking.service;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import org.springframework.stereotype.Service;

@Service
public class RecipeDefinitionToCuringEventIdConverter {

    public int convert(final RecipeDefinition recipeDefinition) {
        switch (recipeDefinition.getId()) {
            case 13:
                return 1;
            default:
                throw new IllegalArgumentException("The recipe: " + recipeDefinition + " doesn't have an event definition!");
        }
    }
}
