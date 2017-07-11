package com.morethanheroic.swords.shop.service.availability;

import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Provide a way to enable or disable access to a shop for a player.
 */
public interface ShopAvailabilityEvaluator {

    /**
     * Check if the provided shop is available to be used by the user.
     *
     * @param userEntity the user to check the availability for
     * @param shopDefinition the shop to check the availability
     * @return true if the user can access to the shop, false otherwise
     */
    boolean isAvailable(final UserEntity userEntity, final ShopDefinition shopDefinition);
}
