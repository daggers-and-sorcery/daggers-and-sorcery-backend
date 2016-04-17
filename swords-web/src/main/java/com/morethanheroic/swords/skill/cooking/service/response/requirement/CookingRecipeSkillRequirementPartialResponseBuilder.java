package com.morethanheroic.swords.skill.cooking.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeSkillRequirement;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeSkillRequirementPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeSkillRequirementPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeRequirementPartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeRequirementPartialResponse build(CookingRecipeRequirementPartialResponseBuilderConfiguration cookingRecipeRequirementPartialResponseBuilderConfiguration) {
        final RecipeSkillRequirement recipeSkillRequirement = (RecipeSkillRequirement) cookingRecipeRequirementPartialResponseBuilderConfiguration.getRecipeRequirement();

        return CookingRecipeSkillRequirementPartialResponse.builder()
                .skill(recipeSkillRequirement.getSkill().name().toLowerCase())
                .amount(recipeSkillRequirement.getAmount())
                .build();
    }
}
