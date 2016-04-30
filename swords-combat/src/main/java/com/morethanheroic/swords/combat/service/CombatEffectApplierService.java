package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatEffectApplierService {

    @Autowired
    private CombatEffectServiceAccessor combatEffectServiceAccessor;

    public void applyEffects(CombatEntity combatEntity, CombatResult combatResult, List<CombatEffectDefinition> combatEffects, CombatEffectDataHolder combatEffectDataHolder) {
        for (CombatEffectDefinition combatEffect : combatEffects) {
            applyEffect(combatEntity, combatResult, combatEffect, combatEffectDataHolder);
        }
    }

    public void applyEffect(CombatEntity combatEntity, CombatResult combatResult, CombatEffectDefinition combatEffect, CombatEffectDataHolder combatEffectDataHolder) {
        combatEffect.apply(combatEntity, combatResult, combatEffectDataHolder, combatEffectServiceAccessor);
    }
}
