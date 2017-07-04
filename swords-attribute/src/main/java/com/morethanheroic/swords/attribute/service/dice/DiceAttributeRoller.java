package com.morethanheroic.swords.attribute.service.dice;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiceAttributeRoller {

    private final DiceRollCalculator diceRollCalculator;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;

    public int rollDices(final DiceAttribute diceAttribute) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(diceAttribute));
    }

    public int rollDices(final DiceValueAttributeCalculationResult diceValueAttributeCalculationResult) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(diceValueAttributeCalculationResult));
    }
}
