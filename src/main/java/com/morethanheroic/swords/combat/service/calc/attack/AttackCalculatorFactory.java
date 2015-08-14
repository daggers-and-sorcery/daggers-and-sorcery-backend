package com.morethanheroic.swords.combat.service.calc.attack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackCalculatorFactory {

    private final MonsterMeleeAttackCalculator monsterMeleeAttackCalculator;
    private final MonsterRangedAttackCalculator monsterRangedAttackCalculator;
    private final PlayerMeleeAttackCalculator playerMeleeAttackCalculator;
    private final PlayerRangedAttackCalculator playerRangedAttackCalculator;

    @Autowired
    public AttackCalculatorFactory(MonsterMeleeAttackCalculator monsterMeleeAttackCalculator, MonsterRangedAttackCalculator monsterRangedAttackCalculator, PlayerMeleeAttackCalculator playerMeleeAttackCalculator, PlayerRangedAttackCalculator playerRangedAttackCalculator) {
        this.monsterMeleeAttackCalculator = monsterMeleeAttackCalculator;
        this.monsterRangedAttackCalculator = monsterRangedAttackCalculator;
        this.playerMeleeAttackCalculator = playerMeleeAttackCalculator;
        this.playerRangedAttackCalculator = playerRangedAttackCalculator;
    }

    public AttackCalculator getAttackCalculator(AttackingUnit attackingUnit, AttackingType attackingType) {
        if(attackingUnit == AttackingUnit.MONSTER && attackingType == AttackingType.MELEE) {
            return monsterMeleeAttackCalculator;
        } else if (attackingUnit == AttackingUnit.MONSTER && attackingType == AttackingType.RANGED) {
            return monsterRangedAttackCalculator;
        } else if (attackingUnit == AttackingUnit.HUMAN && attackingType == AttackingType.MELEE) {
            return playerMeleeAttackCalculator;
        } else {
            return playerRangedAttackCalculator;
        }
    }
}
