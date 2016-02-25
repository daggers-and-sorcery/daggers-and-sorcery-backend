package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.recipe.domain.RecipeEntity;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.RecipeFacade;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.domain.CookingRecipePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingRecipePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeListPartialResponseBuilder implements PartialResponseCollectionBuilder<CookingInfoResponseBuilderConfiguration> {

    private final RecipeFacade recipeFacade;
    private final CookingRecipePartialResponseBuilder cookingRecipePartialResponseBuilder;

    @Override
    public List<CookingRecipePartialResponse> build(CookingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<CookingRecipePartialResponse> result = new ArrayList<>();

        final List<RecipeEntity> recipeEntities = recipeFacade.getAllLearnedRecipes(responseBuilderConfiguration.getUserEntity());
        for (RecipeEntity recipeEntity : recipeEntities) {
            if (recipeEntity.getRecipeDefinition().getType() == RecipeType.COOKING) {
                result.add(cookingRecipePartialResponseBuilder.build(
                        CookingRecipePartialResponseBuilderConfiguration.builder()
                                .recipeDefinition(recipeEntity.getRecipeDefinition())
                                .build()
                ));
            }
        }

        return result;
    }
}
