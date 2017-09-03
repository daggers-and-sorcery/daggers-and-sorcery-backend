package com.morethanheroic.swords.inn.service.server.availability;

import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Used to check the availability of a service in the inn.
 */
public interface InnServiceAvailabilityChecker {

    /**
     * Return true when the service should be available for the user.
     *
     * @param serviceType the type of the service
     * @param userEntity the user entity that we are checking the availability for
     * @return true if the service should be available, false otherwise
     */
    boolean shouldServiceBeAvailable(final ServiceType serviceType, final UserEntity userEntity);
}
