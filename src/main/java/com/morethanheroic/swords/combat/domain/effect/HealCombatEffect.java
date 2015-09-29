package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;

public class HealCombatEffect extends CombatEffect {

    private final int amount;

    public HealCombatEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(CombatEntity combatEntity) {
        combatEntity.increaseActualHealth(amount);
    }
}
