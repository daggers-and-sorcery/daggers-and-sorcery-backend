package com.morethanheroic.swords.skill.cooking.service.response.requirement;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponseBuilderConfiguration;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeRequirementPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeRequirementPartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeRequirementPartialResponse build(CookingRecipeRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeRequirement recipeRequirement = responseBuilderConfiguration.getRecipeRequirement();

        return CookingRecipeRequirementPartialResponse.builder()
                .skill(WordUtils.capitalize(recipeRequirement.getSkill().name().toLowerCase()))
                .amount(recipeRequirement.getAmount())
                .build();
    }
}
