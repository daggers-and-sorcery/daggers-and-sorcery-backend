package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.service.calc.CombatEntity;
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

    public AttackCalculator getAttackCalculator(CombatEntity attackerEntity, AttackType attackType) {
        if(attackerEntity == CombatEntity.MONSTER && attackType == AttackType.MELEE) {
            return monsterMeleeAttackCalculator;
        } else if (attackerEntity == CombatEntity.MONSTER && attackType == AttackType.RANGED) {
            return monsterRangedAttackCalculator;
        } else if (attackerEntity == CombatEntity.HUMAN && attackType == AttackType.MELEE) {
            return playerMeleeAttackCalculator;
        } else {
            return playerRangedAttackCalculator;
        }
    }
}
