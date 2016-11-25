package com.morethanheroic.swords.combat.service.calc.attack.melee;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeleeDamageCalculator {

    private final DiceRollCalculator diceRollCalculator;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;

    public int calculateDamage(final CombatEntity attacker, final CombatEntity opponent) {
        return calculateBaseDamage(attacker) - calculateDamageReduction(opponent);
    }

    private int calculateBaseDamage(final CombatEntity attacker) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getDamage()));
    }

    private int calculateDamageReduction(final CombatEntity opponent) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(opponent.getDamageReduction()));
    }
}
