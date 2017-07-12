package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;

import lombok.RequiredArgsConstructor;

/**
 * Restore given amount of health points to the user.
 */
@Service
@RequiredArgsConstructor
public class RestoreHealthCombatEffectDefinition extends CombatEffectDefinition {

    private final CombatMessageFactory combatMessageFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());

        effectApplyingContext.addCombatStep(
                DefaultCombatStep.builder()
                        .message(combatMessageFactory.newMessage("heal", "RESTORE_HEALTH_EFFECT_SPELL_HEALING_DONE", amount))
                        .build()
        );

        effectApplyingContext.getDestination().getCombatEntity().increaseActualHealth(amount);
    }

    @Override
    public String getId() {
        return "restore_health";
    }
}
