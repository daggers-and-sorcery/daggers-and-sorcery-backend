package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
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
    public void apply(CombatEntity combatEntity, Combat combat, CombatResult combatResult, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        combatEffectServiceAccessor.getInventoryFacade().getInventory(((UserCombatEntity) combatEntity).getUserEntity()).removeItem(item, amount);
    }
}
