package com.morethanheroic.swords.combat.service.dice;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import org.springframework.stereotype.Service;

@Service
public class DiceAttributeToDiceRollCalculationContextConverter {

    public DiceRollCalculationContext convert(final DiceAttribute diceAttribute) {
        return DiceRollCalculationContext.builder()
                .value(diceAttribute.getValue())
                .d2(diceAttribute.getD2())
                .d4(diceAttribute.getD4())
                .d6(diceAttribute.getD6())
                .d8(diceAttribute.getD8())
                .d10(diceAttribute.getD10())
                .build();
    }
}
