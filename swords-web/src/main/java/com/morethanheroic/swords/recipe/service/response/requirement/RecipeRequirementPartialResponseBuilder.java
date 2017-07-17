package com.morethanheroic.swords.recipe.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeRequirementPartialResponseBuilder implements PartialResponseBuilder<RecipeRequirementPartialResponseBuilderConfiguration> {

    private final RecipeSkillRequirementPartialResponseBuilder recipeSkillRequirementPartialResponseBuilder;
    private final RecipeItemRequirementPartialResponseBuilder cookingRecipeItemRequirementPartialResponseBuilder;

    @Override
    public RecipeRequirementPartialResponse build(RecipeRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeRequirement recipeRequirement = responseBuilderConfiguration.getRecipeRequirement();

        if (recipeRequirement instanceof RecipeItemRequirement) {
            return cookingRecipeItemRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
        }

        return recipeSkillRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
    }
}
