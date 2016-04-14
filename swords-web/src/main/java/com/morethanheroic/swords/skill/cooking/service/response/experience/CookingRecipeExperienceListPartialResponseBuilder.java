package com.morethanheroic.swords.skill.cooking.service.response.experience;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.experience.domain.CookingRecipeExperienceListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.response.experience.domain.CookingRecipeExperiencePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.experience.domain.CookingRecipeExperiencePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeExperienceListPartialResponseBuilder
        implements PartialResponseCollectionBuilder<CookingRecipeExperienceListPartialResponseBuilderConfiguration> {

    private final CookingRecipeExperiencePartialResponseBuilder cookingRecipeExperiencePartialResponseBuilder;

    @Override
    public List<CookingRecipeExperiencePartialResponse> build(CookingRecipeExperienceListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<CookingRecipeExperiencePartialResponse> result = new ArrayList<>();

        final List<RecipeExperience> recipeExperiences = responseBuilderConfiguration.getRecipeExperiences();
        for (RecipeExperience recipeExperience : recipeExperiences) {
            result.add(
                    cookingRecipeExperiencePartialResponseBuilder.build(
                            CookingRecipeExperiencePartialResponseBuilderConfiguration.builder()
                                    .recipeExperience(recipeExperience)
                                    .build()
                    )
            );
        }

        return result;
    }
}
