package com.morethanheroic.swords.combat.service.dice;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiceAttributeRoller {

    private final DiceRollCalculator diceRollCalculator;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;

    public int rollDicesForAttribute(final DiceAttribute diceAttribute) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(diceAttribute));
    }
}
