package com.morethanheroic.swords.vampire.service.market.fee;

import com.morethanheroic.swords.market.service.selling.fee.SellingFeeModifier;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Calculate the market selling fee modifications of a vampire.
 */
@Service
@RequiredArgsConstructor
public class VampireSellinFeeModifier implements SellingFeeModifier {

    private static final double VAMPIRE_EXTRA_MARKET_FEE_PERCENTAGE = 0.25;
    private static final double NON_VAMPIRE_EXTRA_MARKET_FEE_PERCENTAGE = 0.25;

    private final VampireCalculator vampireCalculator;

    /**
     * Calculate the modification required to the market fee based on if the user is a vampire or not.
     *
     * @param userEntity the user entity used in the calculation
     * @return the amount of the modification
     */
    @Override
    public double calculateModificationPercentage(final UserEntity userEntity) {
        return vampireCalculator.isVampire(userEntity) ? VAMPIRE_EXTRA_MARKET_FEE_PERCENTAGE : NON_VAMPIRE_EXTRA_MARKET_FEE_PERCENTAGE;
    }
}
