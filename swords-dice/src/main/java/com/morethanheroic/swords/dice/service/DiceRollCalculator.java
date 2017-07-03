package com.morethanheroic.swords.dice.service;

import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Evaluate a {@link DiceRollCalculationContext}. Rolling the necessary dies as stated in the context then returning
 * the result as an integer.
 */
@Service
@RequiredArgsConstructor
public class DiceRollCalculator {

    private static final int D2_DICE_SIDE_COUNT = 2;
    private static final int D4_DICE_SIDE_COUNT = 4;
    private static final int D6_DICE_SIDE_COUNT = 6;
    private static final int D8_DICE_SIDE_COUNT = 8;
    private static final int D10_DICE_SIDE_COUNT = 10;

    private final Random random;

    public int rollDices(final DiceRollCalculationContext diceRollCalculationContext) {
        int result = 0;

        result += rollDiceManyTimes(diceRollCalculationContext.getD2(), D2_DICE_SIDE_COUNT);
        result += rollDiceManyTimes(diceRollCalculationContext.getD4(), D4_DICE_SIDE_COUNT);
        result += rollDiceManyTimes(diceRollCalculationContext.getD6(), D6_DICE_SIDE_COUNT);
        result += rollDiceManyTimes(diceRollCalculationContext.getD8(), D8_DICE_SIDE_COUNT);
        result += rollDiceManyTimes(diceRollCalculationContext.getD10(), D10_DICE_SIDE_COUNT);

        return result + diceRollCalculationContext.getValue();
    }

    private int rollDiceManyTimes(final int times, final int sides) {
        int result = 0;

        for (int i = 0; i < times; i++) {
            result += rollDice(sides);
        }

        return result;
    }

    private int rollDice(final int sides) {
        return random.nextInt(sides) + 1;
    }
}
