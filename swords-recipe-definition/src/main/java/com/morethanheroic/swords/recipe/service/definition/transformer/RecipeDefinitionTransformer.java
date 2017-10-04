package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Transform {@link RawRecipeDefinition} to {@link RecipeDefinition} domain objects.
 */
@Service
@RequiredArgsConstructor
public class RecipeDefinitionTransformer implements DefinitionTransformer<RecipeDefinition, RawRecipeDefinition> {

    private final RecipeExperienceTransformer recipeExperienceTransformer;
    private final RecipeIngredientTransformer recipeIngredientTransformer;
    private final RecipeRewardTransformer recipeRewardTransformer;
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
