package com.morethanheroic.swords.recipe.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeRequirementPartialResponseBuilder implements PartialResponseBuilder<RecipeRequirementPartialResponseBuilderConfiguration> {

    @Autowired
    private RecipeSkillRequirementPartialResponseBuilder recipeSkillRequirementPartialResponseBuilder;

    @Autowired
    private RecipeItemRequirementPartialResponseBuilder cookingRecipeItemRequirementPartialResponseBuilder;

    @Override
    public RecipeRequirementPartialResponse build(RecipeRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeRequirement recipeRequirement = responseBuilderConfiguration.getRecipeRequirement();

        if (recipeRequirement instanceof RecipeItemRequirement) {
            return cookingRecipeItemRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
        }

        return recipeSkillRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
    }
}
