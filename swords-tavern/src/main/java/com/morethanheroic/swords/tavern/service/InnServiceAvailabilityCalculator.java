package com.morethanheroic.swords.tavern.service;

import com.morethanheroic.swords.tavern.domain.service.ServiceType;
import com.morethanheroic.swords.tavern.service.server.availability.InnServiceAvailabilityChecker;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Calculate the availability of the services in the tavern.
 */
@Service
@RequiredArgsConstructor
public class InnServiceAvailabilityCalculator {

    private final List<InnServiceAvailabilityChecker> innServiceAvailabilityCheckers;

    /**
     * Calculate the services that are available fo the provided user.
     *
     * @param userEntity the user we are calculating the availability for
     * @return the available services in the tavern
     */
    public List<ServiceType> getAvailableServices(final UserEntity userEntity) {
        return Arrays.stream(ServiceType.values())
                .filter(
                        serviceType -> innServiceAvailabilityCheckers.stream()
                                .allMatch(innServiceAvailabilityChecker -> innServiceAvailabilityChecker.shouldServiceBeAvailable(serviceType, userEntity))
                )
                .collect(Collectors.toList());
    }
}
