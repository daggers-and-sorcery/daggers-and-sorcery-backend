package com.morethanheroic.swords.combat.service.calc.damage.type;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.DamageCalculator;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.DamageEventCombatStepExtractor;
import com.morethanheroic.swords.combat.service.calc.damage.event.DamageEventRunner;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageEventCalculationResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeleeDamageCalculator implements DamageCalculator {

    private final DiceAttributeRoller diceAttributeRoller;
    private final DamageEventRunner damageEventRunner;
    private final DamageEventCombatStepExtractor damageEventCombatStepExtractor;

    public DamageCalculationResult calculateDamage(final CombatEntity attacker, final CombatEntity opponent) {
        final List<DamageEventCalculationResult> damageEventCalculationResults = damageEventRunner.runEvents(attacker, opponent, DamageType.MELEE);

        return DamageCalculationResult.builder()
                .damage(calculateFinalDamage(attacker, opponent, damageEventCalculationResults))
                .combatSteps(damageEventCombatStepExtractor.extractCombatSteps(damageEventCalculationResults))
                .build();
    }

    private int calculateFinalDamage(final CombatEntity attacker, final CombatEntity opponent, final List<DamageEventCalculationResult> damageEventCalculationResults) {
        final int resultDamage = calculateBaseDamage(attacker) + calculateDamageFromEvents(damageEventCalculationResults) - calculateDamageReduction(opponent);

        return resultDamage > 1 ? resultDamage : 1;
    }

    private int calculateDamageFromEvents(final List<DamageEventCalculationResult> damageEventCalculationResults) {
        return damageEventCalculationResults.stream()
                .map(DamageEventCalculationResult::getBonusDamage)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateBaseDamage(final CombatEntity attacker) {
        return diceAttributeRoller.rollDicesForAttribute(attacker.getDamage());
    }

    private int calculateDamageReduction(final CombatEntity opponent) {
        return diceAttributeRoller.rollDicesForAttribute(opponent.getDamageReduction());
    }
}
