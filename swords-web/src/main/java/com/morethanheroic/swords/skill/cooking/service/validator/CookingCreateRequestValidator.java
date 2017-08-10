package com.morethanheroic.swords.skill.cooking.service.validator;

import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.cooking.view.request.CookingCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookingCreateRequestValidator {

    private final RecipeDefinitionCache recipeDefinitionCache;

    public void validate(CookingCreateRequest cookingCreateRequest) {
        if (!recipeDefinitionCache.hasDefinition(cookingCreateRequest.getRecipeId())) {
            throw new NotFoundException("Recipe definition doesn't exist for id: " + cookingCreateRequest.getRecipeId());
        }
    }
}
