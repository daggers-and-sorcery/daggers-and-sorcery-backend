package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.morethanheroic.swords.combat.domain.effect.entry.domain.CombatEffectIdentifier.RESTORE_MOVEMENT;

@Service
@RequiredArgsConstructor
public class RestoreMovementCombatEffectDefinition extends ImprovedCombatEffectDefinition {

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        final int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());

        effectApplyingContext.getDestination().getCombatEntity().increaseActualMovement(amount);
    }

    @Override
    public String getId() {
        return RESTORE_MOVEMENT.name();
    }
}
