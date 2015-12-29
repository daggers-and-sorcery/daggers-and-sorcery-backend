package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.CombatAttributeCalculationResult;
import com.morethanheroic.swords.combat.domain.DiceAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceUtil {

    private final Random random;

    @Autowired
    public DiceUtil(Random random) {
        this.random = random;
    }

    public int rollValueFromAttributeCalculationResult(AttributeCalculationResult attributeCalculationResult) {
        int result = 0;

        if (attributeCalculationResult instanceof CombatAttributeCalculationResult) {
            result += rollDiceManyTimes(((CombatAttributeCalculationResult) attributeCalculationResult).getD2(), 2);
            result += rollDiceManyTimes(((CombatAttributeCalculationResult) attributeCalculationResult).getD4(), 4);
            result += rollDiceManyTimes(((CombatAttributeCalculationResult) attributeCalculationResult).getD6(), 6);
            result += rollDiceManyTimes(((CombatAttributeCalculationResult) attributeCalculationResult).getD8(), 8);
            result += rollDiceManyTimes(((CombatAttributeCalculationResult) attributeCalculationResult).getD10(), 10);
        }

        return result + attributeCalculationResult.getValue();
    }

    public int rollValueFromDiceAttribute(DiceAttribute diceAttribute) {
        int result = 0;

        result += rollDiceManyTimes(diceAttribute.getD2(), 2);
        result += rollDiceManyTimes(diceAttribute.getD4(), 4);
        result += rollDiceManyTimes(diceAttribute.getD6(), 6);
        result += rollDiceManyTimes(diceAttribute.getD8(), 8);
        result += rollDiceManyTimes(diceAttribute.getD10(), 10);

        return result + diceAttribute.getValue();
    }

    private int rollDiceManyTimes(int times, int sides) {
        int result = 0;

        for (int i = 0; i < times; i++) {
            result += rollDice(sides);
        }

        return result;
    }

    private int rollDice(int sides) {
        return random.nextInt(sides) + 1;
    }
}
