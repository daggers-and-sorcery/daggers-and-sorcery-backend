package com.morethanheroic.swords.recipe.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeSkillRequirement;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeSkillRequirementPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class RecipeSkillRequirementPartialResponseBuilder implements PartialResponseBuilder<RecipeRequirementPartialResponseBuilderConfiguration> {

    @Override
    public RecipeRequirementPartialResponse build(RecipeRequirementPartialResponseBuilderConfiguration recipeRequirementPartialResponseBuilderConfiguration) {
        final RecipeSkillRequirement recipeSkillRequirement = (RecipeSkillRequirement) recipeRequirementPartialResponseBuilderConfiguration.getRecipeRequirement();

        return RecipeSkillRequirementPartialResponse.builder()
                .skill(recipeSkillRequirement.getSkill().getName())
                .amount(recipeSkillRequirement.getAmount())
                .build();
    }
}
