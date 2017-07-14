package com.morethanheroic.swords.recipe.service.response.requirement;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeItemRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponse;
import com.morethanheroic.swords.recipe.service.response.requirement.domain.RecipeRequirementPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeItemRequirementPartialResponseBuilder implements PartialResponseBuilder<RecipeRequirementPartialResponseBuilderConfiguration> {

    private final InventoryEntityFactory inventoryEntityFactory;

    @Override
    public RecipeRequirementPartialResponse build(final RecipeRequirementPartialResponseBuilderConfiguration recipeRequirementPartialResponseBuilderConfiguration) {
        final RecipeItemRequirement recipeItemRequirement = (RecipeItemRequirement) recipeRequirementPartialResponseBuilderConfiguration.getRecipeRequirement();

        return RecipeItemRequirementPartialResponse.builder()
                .itemName(recipeItemRequirement.getItem().getName())
                .requiredAmount(recipeItemRequirement.getAmount())
                .existingAmount(inventoryEntityFactory.getEntity(recipeRequirementPartialResponseBuilderConfiguration.getUserEntity()).getItemAmount(recipeItemRequirement.getItem()))
                .build();
    }
}
