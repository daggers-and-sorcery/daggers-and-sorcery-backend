package com.morethanheroic.swords.combat.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;

@Service
@RequiredArgsConstructor
public class CombatEffectApplierService {

    private final CombatEffectDefinitionRegistry combatEffectDefinitionRegistry;

    public void applyEffects(final List<CombatEffectApplyingContext> effectApplyingContext,
            final CombatEffectDataHolder combatEffectDataHolder) {
        effectApplyingContext.forEach(combatEffect -> applyEffect(combatEffect, combatEffectDataHolder));
    }

    public void applyEffect(final CombatEffectApplyingContext effectApplyingContext,
            final CombatEffectDataHolder combatEffectDataHolder) {
        combatEffectDefinitionRegistry.getDefinition(effectApplyingContext.getEffectSettings().getEffectId())
                .apply(effectApplyingContext, combatEffectDataHolder);
    }
}
