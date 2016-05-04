package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class DiceUtil {

    private final Random random;

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
