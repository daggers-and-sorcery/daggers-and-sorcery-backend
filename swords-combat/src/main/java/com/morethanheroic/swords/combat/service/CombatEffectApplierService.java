package com.morethanheroic.swords.combat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;

@Service
public class CombatEffectApplierService {

    @Autowired
    private CombatEffectServiceAccessor combatEffectServiceAccessor;

    public void applyEffects(CombatEffectApplyingContext effectApplyingContext, List<CombatEffectDefinition> combatEffects, CombatEffectDataHolder combatEffectDataHolder) {
        for (CombatEffectDefinition combatEffect : combatEffects) {
            applyEffect(effectApplyingContext, combatEffect, combatEffectDataHolder);
        }
    }

    public void applyEffect(CombatEffectApplyingContext effectApplyingContext, CombatEffectDefinition combatEffect, CombatEffectDataHolder combatEffectDataHolder) {
        combatEffect.apply(effectApplyingContext, combatEffectDataHolder, combatEffectServiceAccessor);
    }
}
