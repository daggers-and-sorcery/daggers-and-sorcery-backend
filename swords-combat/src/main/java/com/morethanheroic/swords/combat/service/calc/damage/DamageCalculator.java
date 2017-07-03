package com.morethanheroic.swords.combat.service.calc.damage;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;

public interface DamageCalculator {

    DamageCalculationResult calculateDamage(final CombatEntity attacker, final CombatEntity opponent);
}
