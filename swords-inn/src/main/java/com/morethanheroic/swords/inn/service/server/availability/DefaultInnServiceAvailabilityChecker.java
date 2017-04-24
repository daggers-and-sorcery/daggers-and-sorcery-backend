package com.morethanheroic.swords.inn.service.server.availability;

import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Default implementation of the {@link InnServiceAvailabilityChecker}. It returns true for every service.
 */
public class DefaultInnServiceAvailabilityChecker implements InnServiceAvailabilityChecker {

    /**
     * Default implementation of the {@link InnServiceAvailabilityChecker}. It returns true for every service.
     */
    @Override
    public boolean shouldServiceBeAvailable(final ServiceType serviceType, final UserEntity userEntity) {
        return true;
    }
}
