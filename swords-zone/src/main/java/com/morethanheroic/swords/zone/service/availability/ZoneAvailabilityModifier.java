package com.morethanheroic.swords.zone.service.availability;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.zone.domain.ExplorationZone;

public interface ZoneAvailabilityModifier {

    /**
     * Check if a zone is available for the player to explore on.
     *
     * @param userEntity the user to check the availability for
     * @return the result of the check
     */
    boolean isZoneAvailable(final UserEntity userEntity);

    ExplorationZone isModifierFor();
}
