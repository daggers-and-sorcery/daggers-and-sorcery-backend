package com.morethanheroic.swords.combat.service.calc.damage.type;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.DamageCalculator;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.DamageEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.domain.DamageEventResult;
import com.morethanheroic.swords.combat.service.dice.CombatBonusRoller;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RangedDamageCalculator implements DamageCalculator {

    private final DiceAttributeRoller diceAttributeRoller;
    private final DamageEventRunner damageEventRunner;
    private final CombatBonusRoller combatBonusRoller;

    public DamageCalculationResult calculateDamage(final CombatEntity attacker, final CombatEntity opponent) {
        final DamageEventResult damageEventResult = damageEventRunner.runEvents(attacker, opponent, DamageType.RANGED);

        return DamageCalculationResult.builder()
                .damage(calculateFinalDamage(attacker, opponent, damageEventResult))
                .combatSteps(damageEventResult.getCombatSteps())
                .build();
    }

    private int calculateFinalDamage(final CombatEntity attacker, final CombatEntity opponent, final DamageEventResult damageEventResult) {
        final int resultDamage = calculateBaseDamage(attacker) + combatBonusRoller.rollDices(damageEventResult.getBonusDamage()) - calculateDamageReduction(opponent);

        return resultDamage > 1 ? resultDamage : 1;
    }

    private int calculateBaseDamage(final CombatEntity attacker) {
        return diceAttributeRoller.rollDicesForAttribute(attacker.getRangedDamage());
    }

    private int calculateDamageReduction(final CombatEntity opponent) {
        return diceAttributeRoller.rollDicesForAttribute(opponent.getDamageReduction());
    }
}
