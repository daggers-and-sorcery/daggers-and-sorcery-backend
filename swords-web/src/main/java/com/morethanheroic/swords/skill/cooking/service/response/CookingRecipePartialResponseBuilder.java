package com.morethanheroic.swords.skill.cooking.service.response;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.domain.CookingRecipePartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingRecipePartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.response.experience.CookingRecipeExperienceListPartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.experience.domain.CookingRecipeExperienceListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.response.ingredient.CookingRecipeIngredientListPartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.ingredient.domain.CookingRecipeIngredientListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.CookingRecipeRequirementListPartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.reward.CookingRecipeRewardListPartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.reward.domain.CookingRecipeRewardListPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingRecipePartialResponseBuilder implements PartialResponseBuilder<CookingRecipePartialResponseBuilderConfiguration> {

    private final CookingRecipeIngredientListPartialResponseBuilder cookingRecipeIngredientListPartialResponseBuilder;
    private final CookingRecipeRewardListPartialResponseBuilder cookingRecipeRewardListPartialResponseBuilder;
    private final CookingRecipeExperienceListPartialResponseBuilder cookingRecipeExperienceListPartialResponseBuilder;
    private final CookingRecipeRequirementListPartialResponseBuilder cookingRecipeRequirementListPartialResponseBuilder;

    @Override
    public CookingRecipePartialResponse build(CookingRecipePartialResponseBuilderConfiguration cookingRecipePartialResponseBuilderConfiguration) {
        final RecipeDefinition recipeDefinition = cookingRecipePartialResponseBuilderConfiguration.getRecipeDefinition();

        return CookingRecipePartialResponse.builder()
                .id(recipeDefinition.getId())
                .name(recipeDefinition.getName())
                .chance(recipeDefinition.getChance())
                .recipeIngredients(
                        cookingRecipeIngredientListPartialResponseBuilder.build(
                                CookingRecipeIngredientListPartialResponseBuilderConfiguration.builder()
                                        .recipeIngredients(recipeDefinition.getRecipeIngredients())
                                        .build()
                        )
                )
                .recipeRewards(
                        cookingRecipeRewardListPartialResponseBuilder.build(
                                CookingRecipeRewardListPartialResponseBuilderConfiguration.builder()
                                        .recipeRewards(recipeDefinition.getRecipeRewards())
                                        .build()
                        )
                )
                .recipeExperiences(
                        cookingRecipeExperienceListPartialResponseBuilder.build(
                                CookingRecipeExperienceListPartialResponseBuilderConfiguration.builder()
                                        .recipeExperiences(recipeDefinition.getRecipeExperiences())
                                        .build()
                        )
                )
                .recipeRequirements(
                        cookingRecipeRequirementListPartialResponseBuilder.build(
                                CookingRecipeRequirementListPartialResponseBuilderConfiguration.builder()
                                .recipeRequirements(recipeDefinition.getRecipeRequirements())
                                .build()
                        )
                )
                .build();
    }
}
