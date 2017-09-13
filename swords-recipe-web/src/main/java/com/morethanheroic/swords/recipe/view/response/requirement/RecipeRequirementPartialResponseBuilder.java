package com.morethanheroic.swords.recipe.view.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeResourceRequirement;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeRequirementPartialResponseBuilder implements PartialResponseBuilder<RecipeRequirementPartialResponseBuilderConfiguration> {

    private final RecipeSkillRequirementPartialResponseBuilder recipeSkillRequirementPartialResponseBuilder;
    private final RecipeItemRequirementPartialResponseBuilder recipeItemRequirementPartialResponseBuilder;
    private final RecipeResourceRequirementPartialResponseBuilder recipeResourceRequirementPartialResponseBuilder;

    @Override
    public RecipeRequirementPartialResponse build(RecipeRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeRequirement recipeRequirement = responseBuilderConfiguration.getRecipeRequirement();

        if (recipeRequirement instanceof RecipeItemRequirement) {
            return recipeItemRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
        } else if (recipeRequirement instanceof RecipeResourceRequirement) {
            return recipeResourceRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
        }

        return recipeSkillRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
    }
}
