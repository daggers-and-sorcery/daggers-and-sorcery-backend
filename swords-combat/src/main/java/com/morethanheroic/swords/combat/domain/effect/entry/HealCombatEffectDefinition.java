package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

public class HealCombatEffectDefinition extends CombatEffectDefinition {

    private final int amount;

    public HealCombatEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        amount = Integer.parseInt(this.getEffectSetting("amount").getValue());
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        combatEntity.increaseActualHealth(amount);
    }
}
