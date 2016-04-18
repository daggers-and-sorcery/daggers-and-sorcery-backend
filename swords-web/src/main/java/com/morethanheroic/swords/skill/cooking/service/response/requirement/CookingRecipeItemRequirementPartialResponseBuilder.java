package com.morethanheroic.swords.skill.cooking.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeItemRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeItemRequirementPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeRequirementPartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeRequirementPartialResponse build(CookingRecipeRequirementPartialResponseBuilderConfiguration cookingRecipeRequirementPartialResponseBuilderConfiguration) {
        final RecipeItemRequirement recipeItemRequirement = (RecipeItemRequirement) cookingRecipeRequirementPartialResponseBuilderConfiguration.getRecipeRequirement();

        return CookingRecipeItemRequirementPartialResponse.builder()
                .itemName(recipeItemRequirement.getItem().getName())
                .amount(recipeItemRequirement.getAmount())
                .build();
    }
}
