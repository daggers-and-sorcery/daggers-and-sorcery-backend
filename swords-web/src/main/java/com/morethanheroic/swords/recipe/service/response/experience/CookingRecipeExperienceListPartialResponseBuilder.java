package com.morethanheroic.swords.recipe.service.response.experience;

import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.service.response.experience.domain.RecipeExperienceListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.experience.domain.RecipeExperiencePartialResponse;
import com.morethanheroic.swords.recipe.service.response.experience.domain.RecipeExperiencePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipeExperienceListPartialResponseBuilder
        implements PartialResponseCollectionBuilder<RecipeExperienceListPartialResponseBuilderConfiguration> {

    private final RecipeExperiencePartialResponseBuilder recipeExperiencePartialResponseBuilder;

    @Override
    public List<RecipeExperiencePartialResponse> build(RecipeExperienceListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipeExperiencePartialResponse> result = new ArrayList<>();

        final List<RecipeExperience> recipeExperiences = responseBuilderConfiguration.getRecipeExperiences();
        for (RecipeExperience recipeExperience : recipeExperiences) {
            result.add(
                    recipeExperiencePartialResponseBuilder.build(
                            RecipeExperiencePartialResponseBuilderConfiguration.builder()
                                    .recipeExperience(recipeExperience)
                                    .build()
                    )
            );
        }

        return result;
    }
}
