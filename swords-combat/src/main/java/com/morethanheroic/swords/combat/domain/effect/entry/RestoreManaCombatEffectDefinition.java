package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Restore given amount of mana points to the user.
 */
@Service
@RequiredArgsConstructor
public class RestoreManaCombatEffectDefinition extends ImprovedCombatEffectDefinition {

    private final CombatMessageFactory combatMessageFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        final int restorationAmount = effectApplyingContext.getEffectSettings().getSettingAsInt("amount");

        effectApplyingContext.addCombatStep(
                DefaultCombatStep.builder()
                        .message(combatMessageFactory.newMessage("heal", "RESTORE_MANA_EFFECT_SPELL_HEALING_DONE", restorationAmount))
                        .build()
        );

        effectApplyingContext.getDestination().getCombatEntity().increaseActualMana(restorationAmount);
    }

    @Override
    public String getId() {
        return "restore_mana";
    }
}
