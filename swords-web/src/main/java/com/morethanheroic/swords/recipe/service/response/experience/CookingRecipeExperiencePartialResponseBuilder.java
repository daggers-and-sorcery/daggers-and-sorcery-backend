package com.morethanheroic.swords.recipe.service.response.experience;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.experience.domain.CookingRecipeExperiencePartialResponse;
import com.morethanheroic.swords.recipe.service.response.experience.domain.CookingRecipeExperiencePartialResponseBuilderConfiguration;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeExperiencePartialResponseBuilder implements PartialResponseBuilder<CookingRecipeExperiencePartialResponseBuilderConfiguration> {

    @Override
    public CookingRecipeExperiencePartialResponse build(CookingRecipeExperiencePartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeExperience recipeExperience = responseBuilderConfiguration.getRecipeExperience();

        return CookingRecipeExperiencePartialResponse.builder()
                .skill(WordUtils.capitalize(recipeExperience.getSkill().name().toLowerCase()))
                .amount(recipeExperience.getAmount())
                .build();
    }
}
