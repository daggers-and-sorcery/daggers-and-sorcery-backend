package com.morethanheroic.swords.recipe.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.loader.domain.RawRecipeDefinition;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transform {@link RawRecipeDefinition} to {@link RecipeDefinition} domain objects.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeDefinitionTransformer implements DefinitionTransformer<RecipeDefinition, RawRecipeDefinition> {

    @NonNull
    private final RecipeExperienceTransformer recipeExperienceTransformer;

    @NonNull
    private final RecipeIngredientTransformer recipeIngredientTransformer;

    @NonNull
    private final RecipeRewardTransformer recipeRewardTransformer;

    @NonNull
    private final RecipeRequirementTransformer recipeRequirementTransformer;

    @Override
    public RecipeDefinition transform(RawRecipeDefinition rawRecipeDefinition) {
        return RecipeDefinition.builder()
                .id(rawRecipeDefinition.getId())
                .name(rawRecipeDefinition.getName())
                .chance(rawRecipeDefinition.getChance())
                .type(rawRecipeDefinition.getType())
                .recipeExperiences(recipeExperienceTransformer.transform(rawRecipeDefinition.getRawRecipeExperienceList()))
                .recipeIngredients(recipeIngredientTransformer.transform(rawRecipeDefinition.getRecipeIngredientList()))
                .recipeRewards(recipeRewardTransformer.transform(rawRecipeDefinition.getRawRecipeRewardList()))
                .recipeRequirements(recipeRequirementTransformer.transform(rawRecipeDefinition.getRawRecipeRequirementList()))
                .build();
    }
}
