package com.morethanheroic.swords.combat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;

@Service
public class CombatEffectApplierService {

    @Autowired
    private CombatEffectDefinitionRegistry combatEffectDefinitionRegistry;

    public void applyEffects(List<CombatEffectApplyingContext> effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        for (CombatEffectApplyingContext combatEffect : effectApplyingContext) {
            applyEffect(combatEffect, combatEffectDataHolder);
        }
    }

    public void applyEffect(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        combatEffectDefinitionRegistry.getDefinition(effectApplyingContext.getEffectSettings().getEffectId()).apply(effectApplyingContext, combatEffectDataHolder);
    }
}
