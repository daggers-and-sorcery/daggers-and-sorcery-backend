package com.morethanheroic.math;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Contains various calculations with random numbers.
 *
 * @deprecated Use the common-math dependency.
 */
@Service
@Deprecated
@RequiredArgsConstructor
public class RandomCalculator {

    private final Random random;

    /**
     * Roll a random number between the provided minimum and maximum.
     *
     * @param minimum the minimum of the roll
     * @param maximum the maximum of the roll
     * @return the result of the roll
     */
    public int randomNumberBetween(final int minimum, final int maximum) {
        return random.nextInt(maximum - minimum + 1) + minimum;
    }

    /**
     * Return true when the result of a roll between min and max is higher than the provided target.
     *
     * @param minimum the minimum value of the roll
     * @param maximum the maximum value of the roll
     * @param target  the target to hit
     * @return true when the target is hit, false otherwise
     */
    public boolean randomRollForTarget(final int minimum, final int maximum, final int target) {
        return randomNumberBetween(minimum, maximum) > target;
    }
}
