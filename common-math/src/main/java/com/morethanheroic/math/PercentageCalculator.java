package com.morethanheroic.math;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Methods to calculate percentages.
 */
@Service
@RequiredArgsConstructor
public class PercentageCalculator {

    private final Random random;

    public double calculatePercentage(double actualValue, double maximumValue) {
        return actualValue * 100 / maximumValue;
    }

    public boolean calculatePercentageHit(final int valueToHit) {
        return valueToHit >= random.nextInt(100) + 1;
    }
}
