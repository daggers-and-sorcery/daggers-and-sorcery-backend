package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.service.RecipeFacade;
import com.morethanheroic.swords.user.domain.UserEntity;

public class LearnRecipeEffectDefinition extends CombatEffectDefinition {

    private final int recipeItemId;
    private final int recipeId;

    public LearnRecipeEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        recipeItemId = Integer.parseInt(this.getEffectSetting("recipe-item-id").getValue());
        recipeId = Integer.parseInt(this.getEffectSetting("recipe-id").getValue());
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatResult combatResult, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        final UserEntity userEntity = ((UserCombatEntity) combatEntity).getUserEntity();
        final RecipeFacade recipeFacade = combatEffectServiceAccessor.getRecipeFacade();
        final RecipeDefinition recipeDefinition = recipeFacade.getDefinition(recipeId);

        if (!recipeFacade.hasRecipeLearned(userEntity, recipeDefinition)) {
            recipeFacade.learnRecipe(userEntity, recipeDefinition);

            combatEffectServiceAccessor.getInventoryFacade().getInventory(((UserCombatEntity) combatEntity).getUserEntity()).removeItem(recipeItemId, 1);
        }
    }
}
