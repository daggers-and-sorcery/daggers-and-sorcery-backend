package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

/**
 * Heal the user for a given amount of health points.
 */
public class HealCombatEffectDefinition extends CombatEffectDefinition {

    private final int amount;

    public HealCombatEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        amount = Integer.parseInt(this.getEffectSetting("amount").getValue());
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatResult combatResult, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("amount", amount);
        combatMessage.addData("icon", "heal");
        combatMessage.addData("message", "You have been healed for ${amount} health!");

        combatResult.addMessage(combatMessage);

        combatEntity.increaseActualHealth(amount);
    }
}
