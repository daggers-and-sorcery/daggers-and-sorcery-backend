package com.morethanheroic.swords.skill.cooking.service.requirement;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.requirement.domain.CookingRecipeRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.requirement.domain.CookingRecipeRequirementPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeRequirementPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeRequirementPartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeRequirementPartialResponse build(CookingRecipeRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeRequirement recipeRequirement = responseBuilderConfiguration.getRecipeRequirement();

        return CookingRecipeRequirementPartialResponse.builder()
                .skill(recipeRequirement.getSkill())
                .amount(recipeRequirement.getAmount())
                .build();
    }
}
