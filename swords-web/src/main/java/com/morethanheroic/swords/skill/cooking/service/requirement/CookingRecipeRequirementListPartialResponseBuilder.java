package com.morethanheroic.swords.skill.cooking.service.requirement;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.skill.cooking.service.requirement.domain.CookingRecipeRequirementListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.requirement.domain.CookingRecipeRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.requirement.domain.CookingRecipeRequirementPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeRequirementListPartialResponseBuilder implements PartialResponseCollectionBuilder<CookingRecipeRequirementListPartialResponseBuilderConfiguration> {

    private final CookingRecipeRequirementPartialResponseBuilder cookingRecipeIngredientPartialResponseBuilder;

    @Override
    public List<CookingRecipeRequirementPartialResponse> build(CookingRecipeRequirementListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<CookingRecipeRequirementPartialResponse> result = new ArrayList<>();

        final List<RecipeRequirement> recipeRequirements = responseBuilderConfiguration.getRecipeRequirements();
        for (RecipeRequirement recipeRequirement : recipeRequirements) {
            result.add(
                    cookingRecipeIngredientPartialResponseBuilder.build(
                            CookingRecipeRequirementPartialResponseBuilderConfiguration.builder()
                                    .recipeRequirement(recipeRequirement)
                                    .build()
                    )
            );
        }

        return result;
    }
}
