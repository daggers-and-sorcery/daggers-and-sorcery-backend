package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectApplyingContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatEffectApplierService {

    @Autowired
    private CombatEffectServiceAccessor combatEffectServiceAccessor;

    public void applyEffects(EffectApplyingContext effectApplyingContext, CombatEntity combatEntity, Combat combat, CombatResult combatResult, List<CombatEffectDefinition> combatEffects, CombatEffectDataHolder combatEffectDataHolder) {
        for (CombatEffectDefinition combatEffect : combatEffects) {
            applyEffect(effectApplyingContext, combatEntity, combat, combatResult, combatEffect, combatEffectDataHolder);
        }
    }

    public void applyEffect(EffectApplyingContext effectApplyingContext, CombatEntity combatEntity, Combat combat, CombatResult combatResult, CombatEffectDefinition combatEffect, CombatEffectDataHolder combatEffectDataHolder) {
        //TODO:add use for EffectApplyingContext! Refact from here!
        combatEffect.apply(combatEntity, combat, combatResult, combatEffectDataHolder, combatEffectServiceAccessor);
    }
}
