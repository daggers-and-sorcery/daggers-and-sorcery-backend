package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;

@Service
public class RemoveItemAmountEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private InventoryFacade inventoryFacade;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final int item = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("item").getValue());
        final int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());

        inventoryFacade.getInventory(((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity()).removeItem(item, amount);
    }

    @Override
    public String getId() {
        return "remove_item";
    }
}
