package com.morethanheroic.swords.combat.service.calc.damage;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.CombatStepListBuilder;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationContext;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.after.AfterDamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.after.domain.AfterDamageEventContext;
import com.morethanheroic.swords.combat.service.calc.damage.event.after.domain.AfterDamageEventResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.BeforeDamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.domain.BeforeDamageEventContext;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.domain.BeforeDamageEventResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.type.DamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.type.domain.DamageCombatEventRunnerContext;
import com.morethanheroic.swords.combat.service.calc.damage.event.type.domain.DamageCombatEventRunnerResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DamageCalculator {

    private final BeforeDamageCombatEventRunner beforeDamageCombatEventRunner;
    private final AfterDamageCombatEventRunner afterDamageCombatEventRunner;
    private final DamageCombatEventRunner damageCombatEventRunner;

    public DamageCalculationResult calculateDamage(final DamageCalculationContext damageCalculationContext) {
        final CombatEntity attacker = damageCalculationContext.getAttacker();
        final CombatEntity defender = damageCalculationContext.getDefender();

        final BeforeDamageEventResult beforeDamageEventResult = beforeDamageCombatEventRunner.runEvents(
                BeforeDamageEventContext.builder()
                        .attacker(damageCalculationContext.getAttacker())
                        .defender(damageCalculationContext.getDefender())
                        .damageType(damageCalculationContext.getDamageType())
                        .build()
        );

        final DamageCombatEventRunnerResult damageCombatEventRunnerResult = damageCombatEventRunner.runEvents(
                DamageCombatEventRunnerContext.builder()
                        .attacker(attacker)
                        .defender(defender)
                        .damageType(DamageType.MAGIC)
                        .damageBonus(beforeDamageEventResult.getBonusDamage())
                        .build()
        );

        final AfterDamageEventResult afterDamageEventResult = afterDamageCombatEventRunner.runEvents(
                AfterDamageEventContext.builder()
                        .attacker(attacker)
                        .defender(defender)
                        .damageDone(damageCombatEventRunnerResult.getDamage())
                        .build()
        );

        return DamageCalculationResult.builder()
                .damage(damageCombatEventRunnerResult.getDamage())
                .combatSteps(
                        CombatStepListBuilder.builder()
                                .withCombatSteps(beforeDamageEventResult.getCombatSteps())
                                .withCombatSteps(damageCombatEventRunnerResult.getCombatSteps())
                                .withCombatSteps(afterDamageEventResult.getCombatSteps())
                                .build()
                )
                .build();
    }
}
