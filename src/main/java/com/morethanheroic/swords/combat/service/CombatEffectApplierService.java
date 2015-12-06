package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.effect.CombatEffect;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatEffectApplierService {

    private static final Logger logger = LoggerFactory.getLogger(CombatEffectApplierService.class);

    @Autowired
    private CombatEffectServiceAccessor combatEffectServiceAccessor;

    public void applyEffects(CombatEntity combatEntity, List<CombatEffect> combatEffects, CombatEffectDataHolder combatEffectDataHolder) {
        for (CombatEffect combatEffect : combatEffects) {
            applyEffect(combatEntity, combatEffect, combatEffectDataHolder);
        }
    }

    public void applyEffect(CombatEntity combatEntity, CombatEffect combatEffect, CombatEffectDataHolder combatEffectDataHolder) {
        combatEffect.apply(combatEntity, combatEffectDataHolder, combatEffectServiceAccessor);
    }
}
