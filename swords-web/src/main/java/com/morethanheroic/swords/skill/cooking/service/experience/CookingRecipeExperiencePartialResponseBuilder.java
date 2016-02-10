package com.morethanheroic.swords.skill.cooking.service.experience;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.experience.domain.CookingRecipeExperiencePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.experience.domain.CookingRecipeExperiencePartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeExperiencePartialResponseBuilder implements PartialResponseBuilder<CookingRecipeExperiencePartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeExperiencePartialResponse build(CookingRecipeExperiencePartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeExperience recipeExperience = responseBuilderConfiguration.getRecipeExperience();

        return CookingRecipeExperiencePartialResponse.builder()
                .skill(recipeExperience.getSkill())
                .amount(recipeExperience.getAmount())
                .build();
    }
}
