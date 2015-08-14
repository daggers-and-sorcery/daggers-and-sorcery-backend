package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import org.springframework.stereotype.Service;

@Service
public class MonsterRangedAttackCalculator implements AttackCalculator {

    @Override
    public void calculateAttack(CombatResult result, Combat combat) {
        //TODO: ranging!
    }
}
