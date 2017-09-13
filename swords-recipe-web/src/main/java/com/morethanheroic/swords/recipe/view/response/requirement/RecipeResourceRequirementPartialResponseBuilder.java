package com.morethanheroic.swords.recipe.view.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeResourceRequirement;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeResourceRequirementPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeResourceRequirementPartialResponseBuilder implements PartialResponseBuilder<RecipeRequirementPartialResponseBuilderConfiguration> {

    @Override
    public RecipeRequirementPartialResponse build(RecipeRequirementPartialResponseBuilderConfiguration recipeRequirementPartialResponseBuilderConfiguration) {
        final RecipeResourceRequirement recipeResourceRequirement = (RecipeResourceRequirement) recipeRequirementPartialResponseBuilderConfiguration.getRecipeRequirement();

        return RecipeResourceRequirementPartialResponse.builder()
                .resource(recipeResourceRequirement.getResource().getName())
                .amount(recipeResourceRequirement.getAmount())
                .build();
    }
}
