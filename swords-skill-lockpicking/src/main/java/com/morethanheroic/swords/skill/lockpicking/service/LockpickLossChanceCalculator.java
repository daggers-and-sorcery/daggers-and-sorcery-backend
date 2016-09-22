package com.morethanheroic.swords.skill.lockpicking.service;

import com.morethanheroic.math.PercentageCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LockpickLossChanceCalculator {

    private static final int CHANCE_TO_LOST_ON_SUCCESS = 30;
    private static final int CHANCE_TO_LOST_ON_FAIL = 60;

    private final PercentageCalculator percentageCalculator;

    public boolean isLoss(final boolean successfulAttempt) {
        if (successfulAttempt) {
            return percentageCalculator.calculatePercentageHit(CHANCE_TO_LOST_ON_SUCCESS);
        } else {
            return percentageCalculator.calculatePercentageHit(CHANCE_TO_LOST_ON_FAIL);
        }
    }
}
