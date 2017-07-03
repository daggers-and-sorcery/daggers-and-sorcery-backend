package com.morethanheroic.swords.combat.service.calc.damage.type;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.DamageCalculator;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RangedDamageCalculator implements DamageCalculator {

    private final DiceRollCalculator diceRollCalculator;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;

    //TODO!
    public int calculateDamage(final CombatEntity attacker, final CombatEntity opponent) {
        final int resultDamage = calculateBaseDamage(attacker) - calculateDamageReduction(opponent);

        return resultDamage > 1 ? resultDamage : 1;
    }

    private int calculateBaseDamage(final CombatEntity attacker) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getRangedDamage()));
    }

    private int calculateDamageReduction(final CombatEntity opponent) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(opponent.getDamageReduction()));
    }
}
