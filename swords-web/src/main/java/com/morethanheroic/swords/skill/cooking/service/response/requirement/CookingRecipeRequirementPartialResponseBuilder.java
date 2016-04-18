package com.morethanheroic.swords.skill.cooking.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.requirement.domain.CookingRecipeRequirementPartialResponseBuilderConfiguration;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookingRecipeRequirementPartialResponseBuilder implements PartialResponseBuilder<CookingRecipeRequirementPartialResponseBuilderConfiguration> {

    @Autowired
    private CookingRecipeSkillRequirementPartialResponseBuilder cookingRecipeSkillRequirementPartialResponseBuilder;

    @Autowired
    private CookingRecipeItemRequirementPartialResponseBuilder cookingRecipeItemRequirementPartialResponseBuilder;

    @Override
    public CookingRecipeRequirementPartialResponse build(CookingRecipeRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeRequirement recipeRequirement = responseBuilderConfiguration.getRecipeRequirement();

        if (recipeRequirement instanceof RecipeItemRequirement) {
            return cookingRecipeItemRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
        }

        return cookingRecipeSkillRequirementPartialResponseBuilder.build(responseBuilderConfiguration);
    }
}
