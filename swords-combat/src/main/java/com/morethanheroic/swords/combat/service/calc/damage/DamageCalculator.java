package com.morethanheroic.swords.combat.service.calc.damage;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.type.DamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.BeforeDamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.domain.BeforeDamageEventResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.type.domain.DamageCombatEventRunnerContext;
import com.morethanheroic.swords.combat.service.calc.damage.event.type.domain.DamageCombatEventRunnerResult;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DamageCalculator {

    private final BeforeDamageCombatEventRunner beforeDamageCombatEventRunner;
    private final DamageCombatEventRunner damageCombatEventRunner;

    public DamageCalculationResult calculateDamage(final DamageCalculationContext damageCalculationContext) {
        final CombatEntity attacker = damageCalculationContext.getAttacker();
        final CombatEntity defender = damageCalculationContext.getDefender();

        final BeforeDamageEventResult beforeDamageEventResult = beforeDamageCombatEventRunner.runEvents(attacker, defender, DamageType.MAGIC);

        final DamageCombatEventRunnerResult damageCombatEventRunnerResult = damageCombatEventRunner.runEvents(
                DamageCombatEventRunnerContext.builder()
                        .attacker(attacker)
                        .defender(defender)
                        .damageType(DamageType.MAGIC)
                        .damageBonus(beforeDamageEventResult.getBonusDamage())
                        .build()
        );

        return DamageCalculationResult.builder()
                .damage(damageCombatEventRunnerResult.getDamage())
                .combatSteps(beforeDamageEventResult.getCombatSteps())
                .build();
    }
}
