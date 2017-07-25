package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Calculate if the player can access the witchhunter's guild.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildAccessibilityCalculator {

    private final VampireCalculator vampireCalculator;

    /**
     * Calculate if the player can access to the Witchhunter's guild.
     *
     * @param userEntity the user to calculate the accessibility for
     * @return the result of the calculation
     */
    public boolean hasAccess(final UserEntity userEntity) {
        return !vampireCalculator.isVampire(userEntity);
    }
}
