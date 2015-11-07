package com.morethanheroic.swords.regeneration.service.calc;

import com.morethanheroic.swords.user.domain.UserEntity;

public interface RegenerationCalculator {

    int calculateRegeneration(UserEntity user, int durationToCalculate);
}
