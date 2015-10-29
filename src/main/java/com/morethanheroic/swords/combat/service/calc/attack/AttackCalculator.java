package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;

public interface AttackCalculator {

    void calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatResult result);
}
