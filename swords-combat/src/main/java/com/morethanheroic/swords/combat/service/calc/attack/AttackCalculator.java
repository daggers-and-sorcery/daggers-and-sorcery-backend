package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.step.domain.CombatStep;

import java.util.List;

public interface AttackCalculator {

    List<CombatStep> calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext);
}
