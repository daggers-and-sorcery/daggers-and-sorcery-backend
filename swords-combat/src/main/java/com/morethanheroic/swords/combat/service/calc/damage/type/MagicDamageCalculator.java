package com.morethanheroic.swords.combat.service.calc.damage.type;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.DamageCalculator;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.DamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.BeforeDamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.domain.BeforeDamageEventResult;
import com.morethanheroic.swords.combat.bonus.dice.CombatBonusRoller;
import com.morethanheroic.swords.attribute.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.service.calc.damage.event.domain.DamageCombatEventRunnerContext;
import com.morethanheroic.swords.combat.service.calc.damage.event.domain.DamageCombatEventRunnerResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MagicDamageCalculator implements DamageCalculator {

    private final BeforeDamageCombatEventRunner beforeDamageCombatEventRunner;
    private final DamageCombatEventRunner damageCombatEventRunner;

    public DamageCalculationResult calculateDamage(final CombatEntity attacker, final CombatEntity defender) {
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
