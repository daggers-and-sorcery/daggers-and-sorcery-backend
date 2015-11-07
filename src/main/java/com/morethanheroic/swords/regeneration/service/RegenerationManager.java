package com.morethanheroic.swords.regeneration.service;

import com.morethanheroic.swords.regeneration.service.calc.HealthRegenerationCalculator;
import com.morethanheroic.swords.regeneration.service.calc.ManaRegenerationCalculator;
import com.morethanheroic.swords.regeneration.service.calc.MovementRegenerationCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegenerationManager {

    @Autowired
    private HealthRegenerationCalculator healthRegenerationCalculator;

    @Autowired
    private ManaRegenerationCalculator manaRegenerationCalculator;

    @Autowired
    private MovementRegenerationCalculator movementRegenerationCalculator;

    @Autowired
    private RegenerationDateCalculator regenerationDateCalculator;

    public void regenerate(UserEntity user) {
        int durationToCalculate = regenerationDateCalculator.calculatePassedDurationSinceLastRegeneration(user.getLastRegenerationDate().getTime());

        if (durationToCalculate > 0) {
            user.regenerate(
                    healthRegenerationCalculator.calculateRegeneration(user, durationToCalculate),
                    manaRegenerationCalculator.calculateRegeneration(user, durationToCalculate),
                    movementRegenerationCalculator.calculateRegeneration(user, durationToCalculate),
                    regenerationDateCalculator.calculateNewRegenerationDate(user, durationToCalculate)
            );
        }
    }
}
