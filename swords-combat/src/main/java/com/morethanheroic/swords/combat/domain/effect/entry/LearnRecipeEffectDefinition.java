package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.inventory.service.InventoryManipulator;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.recipe.service.learn.DefaultLearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.learn.RecipeLearnerService;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearnRecipeEffectDefinition extends ImprovedCombatEffectDefinition {

    private final RecipeDefinitionCache recipeDefinitionCache;
    private final RecipeLearnerService recipeLearnerService;
    private final DefaultLearnedRecipeEvaluator learnedRecipeEvaluator;
    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryManipulator inventoryManipulator;

    @Override
    public void apply(final CombatEffectApplyingContext effectApplyingContext) {
        final int recipeId = effectApplyingContext.getEffectSettings().getSettingAsInt("recipe-id");
        final int recipeItemId = effectApplyingContext.getCause().getId();

        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();
        final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(recipeId);

        if (!learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)) {
            recipeLearnerService.learnRecipe(userEntity, recipeDefinition);

            inventoryManipulator.removeItem(userEntity, itemDefinitionCache.getDefinition(recipeItemId), 1);
        }
    }

    @Override
    public String getId() {
        return "learn_recipe";
    }
}
