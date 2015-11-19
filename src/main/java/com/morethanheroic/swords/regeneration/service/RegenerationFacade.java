package com.morethanheroic.swords.regeneration.service;

import com.morethanheroic.swords.regeneration.service.calc.HealthRegenerationCalculator;
import com.morethanheroic.swords.regeneration.service.calc.ManaRegenerationCalculator;
import com.morethanheroic.swords.regeneration.service.calc.MovementRegenerationCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegenerationFacade {

    @Autowired
    private HealthRegenerationCalculator healthRegenerationCalculator;

    @Autowired
    private ManaRegenerationCalculator manaRegenerationCalculator;

    @Autowired
    private MovementRegenerationCalculator movementRegenerationCalculator;

    @Autowired
    private RegenerationDateCalculator regenerationDateCalculator;

    public void regenerate(UserEntity user) {
        int durationToRegenerate = calculateTheDurationToRegenerate(user);

        if (durationToRegenerate > 0) {
            user.regenerate(
                    healthRegenerationCalculator.calculateRegeneration(user, durationToRegenerate),
                    manaRegenerationCalculator.calculateRegeneration(user, durationToRegenerate),
                    movementRegenerationCalculator.calculateRegeneration(user, durationToRegenerate),
                    regenerationDateCalculator.calculateNewRegenerationDate(user, durationToRegenerate)
            );
        }
    }

    private int calculateTheDurationToRegenerate(UserEntity user) {
        return regenerationDateCalculator.calculatePassedDurationSinceLastRegeneration(user.getLastRegenerationDate().getTime());
    }
}
