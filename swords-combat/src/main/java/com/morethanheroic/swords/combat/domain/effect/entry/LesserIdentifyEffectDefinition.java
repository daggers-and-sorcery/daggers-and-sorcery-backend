package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;

@Service
public class LesserIdentifyEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity());

        final int realItem = unidentifiedItemIdCalculator.getRealItemId(combatEffectDataHolder.getSessionEntity(), Integer.parseInt((String) combatEffectDataHolder.getParameters().get("itemId")));

        if (inventoryEntity.hasItem(realItem, false)) {
            inventoryEntity.removeItem(realItem, 1, false);
            inventoryEntity.addItem(realItem, 1, true);
        }
    }

    @Override
    public String getId() {
        return "lesser_identify";
    }
}
