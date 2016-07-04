package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeFacade;
import com.morethanheroic.swords.user.domain.UserEntity;

@Service
public class LearnRecipeEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private RecipeFacade recipeFacade;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final int recipeItemId = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("recipe-item-id").getValue());
        final int recipeId = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("recipe-id").getValue());

        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();
        final RecipeDefinition recipeDefinition = recipeFacade.getDefinition(recipeId);

        if (!recipeFacade.hasRecipeLearned(userEntity, recipeDefinition)) {
            recipeFacade.learnRecipe(userEntity, recipeDefinition);

            inventoryFacade.getInventory(userEntity).removeItem(recipeItemId, 1);
        }
    }

    @Override
    public String getId() {
        return "learn_recipe";
    }
}
