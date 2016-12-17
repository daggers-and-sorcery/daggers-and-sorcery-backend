package com.morethanheroic.swords.market.service.selling.fee;

import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Calculate the selling fee of a {@link MarketEntity}.
 */
@Service
@RequiredArgsConstructor
public class SellingFeeCalculator {

    private static final double BASE_FEE_PERCENTAGE = 0.1;

    private final List<SellingFeeModifier> sellingFeeModifiers;

    /**
     * Calculate the selling fee of a {@link MarketEntity}.
     *
     * @param userEntity   the user's entity we are calculating the fee for
     * @param marketEntity the market entity to calculate the fee for
     * @return the calculated fee
     */
    public int calculateSellingFee(final UserEntity userEntity, final MarketEntity marketEntity) {
        return (int) Math.ceil(marketEntity.getPrice() * calculateFeePercentage(userEntity));
    }

    private double calculateFeePercentage(final UserEntity userEntity) {
        return BASE_FEE_PERCENTAGE + calculateModifiersPercentage(userEntity);
    }

    private double calculateModifiersPercentage(final UserEntity userEntity) {
        return sellingFeeModifiers.stream()
                .map(sellingFeeModifier -> sellingFeeModifier.calculateModificationPercentage(userEntity))
                .mapToDouble(n -> n)
                .sum();
    }
}
