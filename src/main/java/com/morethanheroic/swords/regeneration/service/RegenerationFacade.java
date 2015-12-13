package com.morethanheroic.swords.regeneration.service;

import com.morethanheroic.swords.regeneration.domain.RegenerationEntity;
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

    @Autowired
    private UserMapper userMapper;

    public RegenerationEntity getEntity(UserEntity userEntity) {
        return new RegenerationEntity(userEntity.getUserDatabaseEntity(), userMapper);
    }

    public void regenerate(UserEntity user) {
        int durationToRegenerate = calculateTheDurationToRegenerate(user);

        if (durationToRegenerate > 0) {
            getEntity(user).regenerate(
                    healthRegenerationCalculator.calculateRegeneration(user, durationToRegenerate),
                    manaRegenerationCalculator.calculateRegeneration(user, durationToRegenerate),
                    movementRegenerationCalculator.calculateRegeneration(user, durationToRegenerate),
                    regenerationDateCalculator.calculateNewRegenerationDate(user, durationToRegenerate)
            );
        }
    }

    private int calculateTheDurationToRegenerate(UserEntity user) {
        return regenerationDateCalculator.calculatePassedDurationSinceLastRegeneration(Instant.from(user.getLastRegenerationDate()));
    }
}
