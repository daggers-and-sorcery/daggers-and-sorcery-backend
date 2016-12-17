package com.morethanheroic.swords.vampire.service.inn.service;

import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.inn.service.server.availability.InnServiceAvailabilityChecker;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Disable the services in the inn when the player is a vampire.
 */
@Service
@RequiredArgsConstructor
public class VampireInnServiceAvailabilityChecker implements InnServiceAvailabilityChecker {

    private final VampireCalculator vampireCalculator;

    @Override
    public boolean shouldServiceBeAvailable(ServiceType serviceType, UserEntity userEntity) {
        return !vampireCalculator.isVampire(userEntity);
    }
}
