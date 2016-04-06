package com.morethanheroic.swords.combat.service.calc;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomCombatCalculator {

    private final Random random = new Random();

    public int calculateWithRandomResult(int value) {
        return normalizeResult(calculateRandomizedResult(value,calculateThirtyPercent(value)));
    }

    private int calculateThirtyPercent(int value) {
        return (int) Math.round(value * 0.3);
    }

    private int calculateRandomizedResult(int value, int thirtyPercent) {
        int result = 0;
        if (thirtyPercent > 0) {
            result = random.nextInt(thirtyPercent) + value;
        }

        return result;
    }

    private int normalizeResult(int result) {
        return result > 0 ? result : 1;
    }
}
