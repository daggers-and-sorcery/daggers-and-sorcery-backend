package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;

public abstract class CombatEffect {

    public abstract void apply(CombatEntity combatEntity);
}
