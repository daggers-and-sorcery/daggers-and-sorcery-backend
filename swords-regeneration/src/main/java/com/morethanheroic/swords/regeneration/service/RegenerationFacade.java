package com.morethanheroic.swords.regeneration.service;

import com.morethanheroic.swords.regeneration.service.calc.HealthRegenerationCalculator;
import com.morethanheroic.swords.regeneration.service.calc.ManaRegenerationCalculator;
import com.morethanheroic.swords.regeneration.service.calc.MovementRegenerationCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

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

    public void regenerate(UserEntity userEntity) {
        final int durationToRegenerate = calculateTheDurationToRegenerate(userEntity);

        if (durationToRegenerate > 0) {
            userEntity.setHealthPoints(healthRegenerationCalculator.calculateRegeneration(userEntity, durationToRegenerate));
            userEntity.setManaPoints(manaRegenerationCalculator.calculateRegeneration(userEntity, durationToRegenerate));
            userEntity.setMovementPoints(movementRegenerationCalculator.calculateRegeneration(userEntity, durationToRegenerate));
            userEntity.updateLastRegenerationDate(regenerationDateCalculator.calculateNewRegenerationDate(userEntity, durationToRegenerate));
        }
    }

    private int calculateTheDurationToRegenerate(UserEntity user) {
        return regenerationDateCalculator.calculatePassedDurationSinceLastRegeneration(Instant.from(user.getLastRegenerationDate()));
    }
}
