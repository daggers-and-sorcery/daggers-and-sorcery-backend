package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;

import java.util.List;

public interface AttackCalculator {

    List<CombatStep> calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext);
}
