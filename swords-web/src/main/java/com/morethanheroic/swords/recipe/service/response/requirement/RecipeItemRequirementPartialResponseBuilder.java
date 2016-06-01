package com.morethanheroic.swords.recipe.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeItemRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class RecipeItemRequirementPartialResponseBuilder implements PartialResponseBuilder<RecipeRequirementPartialResponseBuilderConfiguration> {

    @Override
    public RecipeRequirementPartialResponse build(RecipeRequirementPartialResponseBuilderConfiguration recipeRequirementPartialResponseBuilderConfiguration) {
        final RecipeItemRequirement recipeItemRequirement = (RecipeItemRequirement) recipeRequirementPartialResponseBuilderConfiguration.getRecipeRequirement();

        return RecipeItemRequirementPartialResponse.builder()
                .itemName(recipeItemRequirement.getItem().getName())
                .amount(recipeItemRequirement.getAmount())
                .build();
    }
}
