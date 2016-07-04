package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;

/**
 * Heal the user for a given amount of health points.
 */
@Service
public class HealCombatEffectDefinition extends CombatEffectDefinition {

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final CombatMessage combatMessage = new CombatMessage();

        final int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());

        combatMessage.addData("amount", amount);
        combatMessage.addData("icon", "heal");
        combatMessage.addData("message", "You have been healed for ${amount} health!");

        effectApplyingContext.getCombatResult().addMessage(combatMessage);

        effectApplyingContext.getDestination().getCombatEntity().increaseActualHealth(amount);
    }

    @Override
    public String getId() {
        return "heal";
    }
}
