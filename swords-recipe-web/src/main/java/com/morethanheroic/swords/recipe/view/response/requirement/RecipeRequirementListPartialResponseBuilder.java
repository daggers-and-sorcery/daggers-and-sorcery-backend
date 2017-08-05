package com.morethanheroic.swords.recipe.view.response.requirement;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.view.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeRequirementListPartialResponseBuilder implements PartialResponseCollectionBuilder<RecipeRequirementListPartialResponseBuilderConfiguration> {

    private final RecipeRequirementPartialResponseBuilder recipeRequirementPartialResponseBuilder;

    @Override
    public List<RecipeRequirementPartialResponse> build(final RecipeRequirementListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return responseBuilderConfiguration.getRecipeRequirements().stream()
                .map(recipeRequirement -> recipeRequirementPartialResponseBuilder.build(
                        RecipeRequirementPartialResponseBuilderConfiguration.builder()
                                .userEntity(responseBuilderConfiguration.getUserEntity())
                                .recipeRequirement(recipeRequirement)
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }
}
