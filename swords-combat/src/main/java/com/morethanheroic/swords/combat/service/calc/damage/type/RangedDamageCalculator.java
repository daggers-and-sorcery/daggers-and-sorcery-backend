package com.morethanheroic.swords.combat.service.calc.damage.type;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.DamageCalculator;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.BeforeDamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.domain.BeforeDamageEventResult;
import com.morethanheroic.swords.combat.bonus.dice.CombatBonusRoller;
import com.morethanheroic.swords.attribute.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RangedDamageCalculator implements DamageCalculator {

    private final DiceAttributeRoller diceAttributeRoller;
    private final BeforeDamageCombatEventRunner beforeDamageCombatEventRunner;
    private final CombatBonusRoller combatBonusRoller;

    public DamageCalculationResult calculateDamage(final CombatEntity attacker, final CombatEntity opponent) {
        final BeforeDamageEventResult beforeDamageEventResult = beforeDamageCombatEventRunner.runEvents(attacker, opponent, DamageType.RANGED);

        return DamageCalculationResult.builder()
                .damage(calculateFinalDamage(attacker, opponent, beforeDamageEventResult))
                .combatSteps(beforeDamageEventResult.getCombatSteps())
                .build();
    }

    private int calculateFinalDamage(final CombatEntity attacker, final CombatEntity opponent, final BeforeDamageEventResult beforeDamageEventResult) {
        final int resultDamage = calculateBaseDamage(attacker) + combatBonusRoller.rollDices(beforeDamageEventResult.getBonusDamage()) - calculateDamageReduction(opponent);

        return resultDamage > 1 ? resultDamage : 1;
    }

    private int calculateBaseDamage(final CombatEntity attacker) {
        return diceAttributeRoller.rollDices(attacker.getRangedDamage());
    }

    private int calculateDamageReduction(final CombatEntity opponent) {
        return diceAttributeRoller.rollDices(opponent.getDamageReduction());
    }
}
