package com.morethanheroic.swords.combat.service.attack;

import java.util.List;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.calc.AttackCalculatorProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonsterAttackCalculator {

    private final AttackCalculatorProvider attackCalculatorProvider;

    public List<CombatStep> monsterAttack(final CombatContext combatContext) {
        return attackCalculatorProvider.getAttackCalculatorForAttackType(combatContext.getOpponent().getAttackType())
                                       .calculateAttack(combatContext.getOpponent(), combatContext.getUser(), combatContext);
    }
}
