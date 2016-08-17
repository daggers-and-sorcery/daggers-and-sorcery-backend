package com.morethanheroic.swords.combat.service.calc;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.service.calc.attack.AttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.attack.MagicAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.MeleeAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.RangedAttackCalculator;
import com.morethanheroic.swords.monster.domain.MonsterAttackType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttackCalculatorProvider {

    private final MeleeAttackCalculator meleeAttackCalculator;
    private final RangedAttackCalculator rangedAttackCalculator;
    private final MagicAttackCalculator magicAttackCalculator;

    public AttackCalculator getAttackCalculatorForAttackType(final AttackType attackType) {
        if (attackType == AttackType.RANGED) {
            return rangedAttackCalculator;
        } else if (attackType == AttackType.MAGIC) {
            return magicAttackCalculator;
        } else {
            return meleeAttackCalculator;
        }
    }

    public AttackCalculator getAttackCalculatorForAttackType(MonsterAttackType monsterAttackType) {
        if (monsterAttackType == MonsterAttackType.RANGED) {
            return rangedAttackCalculator;
        } else if (monsterAttackType == MonsterAttackType.MAGIC) {
            return magicAttackCalculator;
        } else {
            return meleeAttackCalculator;
        }
    }
}
