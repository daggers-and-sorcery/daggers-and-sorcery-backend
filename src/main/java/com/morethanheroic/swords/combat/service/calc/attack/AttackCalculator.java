package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;

public interface AttackCalculator {

    void calculateAttack(CombatResult result, Combat combat);
}
