package com.morethanheroic.swords.tavern.service.vampire;

import com.morethanheroic.swords.tavern.domain.service.ServiceType;
import com.morethanheroic.swords.tavern.service.server.availability.InnServiceAvailabilityChecker;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Disable the services in the tavern when the player is a vampire.
 */
@Service
@RequiredArgsConstructor
public class VampireTavernServiceAvailabilityChecker implements InnServiceAvailabilityChecker {

    private final VampireCalculator vampireCalculator;

    @Override
    public boolean shouldServiceBeAvailable(final ServiceType serviceType, final UserEntity userEntity) {
        return !vampireCalculator.isVampire(userEntity);
    }
}
