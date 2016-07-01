package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

public class RemoveItemAmountEffectDefinition extends CombatEffectDefinition {

    private final int item;
    private final int amount;

    public RemoveItemAmountEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        item = Integer.parseInt(this.getEffectSetting("item").getValue());
        amount = Integer.parseInt(this.getEffectSetting("amount").getValue());
    }

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        combatEffectServiceAccessor.getInventoryFacade().getInventory(((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity()).removeItem(item, amount);
    }

    @Override
    public String getId() {
        return "remove_item";
    }
}
