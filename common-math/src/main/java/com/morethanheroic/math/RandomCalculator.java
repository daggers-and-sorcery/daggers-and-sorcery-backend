package com.morethanheroic.math;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RandomCalculator {

    private final Random random;

    public int randomNumberBetween(final int minimum, final int maximum) {
        return random.nextInt(maximum - minimum + 1) + minimum;
    }
}
