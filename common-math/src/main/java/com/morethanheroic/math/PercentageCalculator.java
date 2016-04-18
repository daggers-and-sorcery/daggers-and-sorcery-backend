package com.morethanheroic.math;

import org.springframework.stereotype.Service;

@Service
public class PercentageCalculator {

    public double calculatePercentage(double actualValue, double maximumValue) {
        return actualValue * 100 / maximumValue;
    }
}
