package com.morethanheroic.swords.recipe.service.response.requirement;

import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeRequirementListPartialResponseBuilder implements PartialResponseCollectionBuilder<RecipeRequirementListPartialResponseBuilderConfiguration> {

    private final RecipeRequirementPartialResponseBuilder recipeRequirementPartialResponseBuilder;

    @Override
    public List<RecipeRequirementPartialResponse> build(RecipeRequirementListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final List<RecipeRequirementPartialResponse> result = new ArrayList<>();

        final List<RecipeRequirement> recipeRequirements = responseBuilderConfiguration.getRecipeRequirements();
        for (RecipeRequirement recipeRequirement : recipeRequirements) {
            result.add(
                    recipeRequirementPartialResponseBuilder.build(
                            RecipeRequirementPartialResponseBuilderConfiguration.builder()
                                    .recipeRequirement(recipeRequirement)
                                    .build()
                    )
            );
        }

        return result;
    }
}
