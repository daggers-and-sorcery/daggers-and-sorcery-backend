package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
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
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        final int recipeItemId = effectApplyingContext.getEffectSettings().getSettingAsInt("recipe-item-id");
        final int recipeId = effectApplyingContext.getEffectSettings().getSettingAsInt("recipe-id");

        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();
        final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(recipeId);

        if (!learnedRecipeEvaluator.hasRecipeLearned(userEntity, recipeDefinition)) {
            recipeLearnerService.learnRecipe(userEntity, recipeDefinition);

            inventoryEntityFactory.getEntity(userEntity).removeItem(itemDefinitionCache.getDefinition(recipeItemId), 1);
        }
    }

    @Override
    public String getId() {
        return "learn_recipe";
    }
}
