package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;

import lombok.RequiredArgsConstructor;

/**
 * Heal the user for a given amount of health points.
 */
@Service
@RequiredArgsConstructor
public class HealCombatEffectDefinition extends CombatEffectDefinition {

    private final CombatMessageFactory combatMessageFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());

        effectApplyingContext.addCombatStep(
            DefaultCombatStep.builder()
                             .message(combatMessageFactory.newMessage("heal", "HEAL_EFFECT_SPELL_HEALING_DONE", amount))
                             .build()
        );

        effectApplyingContext.getDestination().getCombatEntity().increaseActualHealth(amount);
    }

    @Override
    public String getId() {
        return "heal";
    }
}
