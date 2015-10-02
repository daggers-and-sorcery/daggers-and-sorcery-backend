package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombatEffectApplierService {

    private static final Logger logger = LoggerFactory.getLogger(CombatEffectApplierService.class);

    public void applyEffects(CombatEntity combatEntity, List<CombatEffect> combatEffects) {
        for (CombatEffect combatEffect : combatEffects) {
            logger.debug("Running combat effect: " + combatEffect);

            applyEffect(combatEntity, combatEffect);
        }
    }

    public void applyEffect(CombatEntity combatEntity, CombatEffect combatEffect) {
        combatEffect.apply(combatEntity);
    }
}
