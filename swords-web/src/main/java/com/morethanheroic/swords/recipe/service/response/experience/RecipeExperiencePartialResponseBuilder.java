package com.morethanheroic.swords.recipe.service.response.experience;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.experience.domain.RecipeExperiencePartialResponse;
import com.morethanheroic.swords.recipe.service.response.experience.domain.RecipeExperiencePartialResponseBuilderConfiguration;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

@Service
public class RecipeExperiencePartialResponseBuilder implements PartialResponseBuilder<RecipeExperiencePartialResponseBuilderConfiguration> {

    @Override
    public RecipeExperiencePartialResponse build(RecipeExperiencePartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeExperience recipeExperience = responseBuilderConfiguration.getRecipeExperience();

        return RecipeExperiencePartialResponse.builder()
                .skill(WordUtils.capitalize(recipeExperience.getSkill().name().toLowerCase()))
                .amount(recipeExperience.getAmount())
                .build();
    }
}
