package com.morethanheroic.swords.market.service.selling.fee;

import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * A modification on the selling fee.
 */
public interface SellingFeeModifier {

    /**
     * Calculate the percentage bonus of the modification.
     *
     * @param userEntity the user entity used in the calculation
     * @return the amount of the modification
     */
    double calculateModificationPercentage(final UserEntity userEntity);
}
