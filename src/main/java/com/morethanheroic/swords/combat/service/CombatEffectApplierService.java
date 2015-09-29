package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import org.springframework.stereotype.Service;

@Service
public class CombatEffectApplierService {

    public void applyEffect(CombatEntity combatEntity, CombatEffect combatEffect) {
        combatEffect.apply(combatEntity);
    }
}
